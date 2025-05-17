package com.coaching.paatthya

import com.coaching.paatthya.domain.logger.Logger
import com.coaching.paatthya.domain.model.User
import com.coaching.paatthya.domain.repository.AuthError
import com.coaching.paatthya.domain.repository.AuthRepository
import com.coaching.paatthya.domain.repository.FirestoreDbError
import com.coaching.paatthya.domain.repository.Result
import com.coaching.paatthya.domain.repository.UserRepository
import com.coaching.paatthya.domain.usecase.AuthDataValidation
import com.coaching.paatthya.domain.usecase.SignUpUseCase
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SignUpUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var logger: Logger

    @Mock
    private lateinit var authRepository: AuthRepository

    @Mock
    private lateinit var authValidator: AuthDataValidation

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var signUpUseCase: SignUpUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        signUpUseCase = SignUpUseCase(authRepository, authValidator, userRepository, logger)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `returns error for invalid email`() = runTest {
        val email = "bademail"
        val password = "Password123"

        `when`(authValidator.validateName(email)).thenReturn(Result.Error(AuthError.EmailValidationError.EMAIL_INVALID))

        val result = signUpUseCase.executeSignUp(email, password)

        assertTrue(result is Result.Error)
    }

    @Test
    fun `returns error for invalid password`() = runTest {
        val email = "test@example.com"
        val password = "123"

        `when`(authValidator.validateName(email)).thenReturn(Result.Success(Unit))
        `when`(authValidator.validatePassword(password)).thenReturn(Result.Error(AuthError.PasswordValidationError.TOO_SHORT))

        val result = signUpUseCase.executeSignUp(email, password)

        assertTrue(result is Result.Error)
    }

    @Test
    fun `returns error when sign-up fails in Firebase`() = runTest {
        val email = "test@example.com"
        val password = "Password123"

        `when`(authValidator.validateName(email)).thenReturn(Result.Success(Unit))
        `when`(authValidator.validatePassword(password)).thenReturn(Result.Success(Unit))
        `when`(authRepository.signUpWithEmailAndPassword(email, password)).thenReturn(Result.Error(AuthError.SignInError.INVALID_CREDENTIALS))

        val result = signUpUseCase.executeSignUp(email, password)

        assertTrue(result is Result.Error)
    }

    @Test
    fun `signs up but fails Firestore update`() = runTest {
        val email = "test@example.com"
        val password = "Password123"
        val user = User(uid = "uid123", email = email)

        `when`(authValidator.validateName(email)).thenReturn(Result.Success(Unit))
        `when`(authValidator.validatePassword(password)).thenReturn(Result.Success(Unit))
        `when`(authRepository.signUpWithEmailAndPassword(email, password)).thenReturn(Result.Success(user))
        `when`(userRepository.updateUserDatabase(user)).thenReturn(Result.Error(FirestoreDbError.DBError.SERVER_ERROR))

        val result = signUpUseCase.executeSignUp(email, password)

        assertTrue(result is Result.Error)
        verify(authRepository).signOut()
    }

    @Test
    fun `successful sign-up`() = runTest {
        val email = "test@example.com"
        val password = "Password123"
        val user = User(uid = "uid123", email = email)

        `when`(authValidator.validateName(email)).thenReturn(Result.Success(Unit))
        `when`(authValidator.validatePassword(password)).thenReturn(Result.Success(Unit))
        `when`(authRepository.signUpWithEmailAndPassword(email, password)).thenReturn(Result.Success(user))
        `when`(userRepository.updateUserDatabase(user)).thenReturn(Result.Success(user))

        val result = signUpUseCase.executeSignUp(email, password)

        assertTrue(result is Result.Success)
        assertEquals(user, (result as Result.Success).data)
        verify(logger).log("SignUp successful for user: $email")
    }
}

