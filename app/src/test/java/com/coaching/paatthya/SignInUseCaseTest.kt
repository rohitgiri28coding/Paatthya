package com.coaching.paatthya

import com.coaching.paatthya.domain.logger.Logger
import com.coaching.paatthya.domain.model.User
import com.coaching.paatthya.domain.repository.AuthError
import com.coaching.paatthya.domain.repository.AuthRepository
import com.coaching.paatthya.domain.repository.FirestoreDbError
import com.coaching.paatthya.domain.repository.Result
import com.coaching.paatthya.domain.repository.UserRepository
import com.coaching.paatthya.domain.usecase.AuthDataValidation
import com.coaching.paatthya.domain.usecase.SignInUseCase
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
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SignInUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var logger: Logger

    @Mock
    private lateinit var authRepository: AuthRepository

    @Mock
    private lateinit var authValidator: AuthDataValidation

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        signInUseCase = SignInUseCase(authRepository, authValidator, userRepository, logger)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `returns error for invalid email`() = runTest {
        val email = "invalid"
        val password = "Password123"

        `when`(authValidator.validateEmail(email)).thenReturn(Result.Error(AuthError.EmailValidationError.EMAIL_INVALID))

        val result = signInUseCase.executeSignIn(email, password)

        assertTrue(result is Result.Error)
        assertEquals(AuthError.EmailValidationError.EMAIL_INVALID, (result as Result.Error).error)
    }


    @Test
    fun `returns error for invalid password`() = runTest {
        val email = "test@example.com"
        val password = "short"

        `when`(authValidator.validateEmail(email)).thenReturn(Result.Success(Unit))
        `when`(authValidator.validatePassword(password)).thenReturn(Result.Error(AuthError.PasswordValidationError.TOO_SHORT))

        val result = signInUseCase.executeSignIn(email, password)

        assertTrue(result is Result.Error)
        assertEquals(AuthError.PasswordValidationError.TOO_SHORT, (result as Result.Error).error)
    }

    @Test
    fun `returns error when Firebase sign-in fails`() = runTest {
        val email = "test@example.com"
        val password = "Password123"

        `when`(authValidator.validateEmail(email)).thenReturn(Result.Success(Unit))
        `when`(authValidator.validatePassword(password)).thenReturn(Result.Success(Unit))
        `when`(authRepository.signInWithEmailAndPassword(email, password)).thenReturn(
            Result.Error(
                AuthError.SignInError.INVALID_CREDENTIALS
            )
        )

        val result = signInUseCase.executeSignIn(email, password)

        assertTrue(result is Result.Error)
        assertEquals(AuthError.SignInError.INVALID_CREDENTIALS, (result as Result.Error).error)
    }

    @Test
    fun `signs in but fails Firestore update`() = runTest {
        val email = "test@example.com"
        val password = "Password123"
        val firebaseUser = User(
            "uid123", email = email,
            batches = emptyList(),
        )

        `when`(authValidator.validateEmail(email)).thenReturn(Result.Success(Unit))
        `when`(authValidator.validatePassword(password)).thenReturn(Result.Success(Unit))
        `when`(
            authRepository.signInWithEmailAndPassword(
                email,
                password
            )
        ).thenReturn(Result.Success(firebaseUser))
        `when`(userRepository.updateUserDatabase(firebaseUser)).thenReturn(
            Result.Error(
                FirestoreDbError.DBError.SERVER_ERROR
            )
        )

        val result = signInUseCase.executeSignIn(email, password)

        verify(authRepository).signOut()
        assertTrue(result is Result.Error)
        assertEquals(FirestoreDbError.DBError.SERVER_ERROR, (result as Result.Error).error)
    }

    @Test
    fun `successful sign-in and firestore update`() = runTest {
        val email = "test@example.com"
        val password = "Password123"
        val firebaseUser = User(
            "uid123", email = email,
            batches = emptyList(),
        )
        `when`(authValidator.validateEmail(email)).thenReturn(Result.Success(Unit))
        `when`(authValidator.validatePassword(password)).thenReturn(Result.Success(Unit))
        `when`(
            authRepository.signInWithEmailAndPassword(
                email,
                password
            )
        ).thenReturn(Result.Success(firebaseUser))
        `when`(userRepository.updateUserDatabase(firebaseUser)).thenReturn(
            Result.Success(
                firebaseUser
            )
        )

        val result = signInUseCase.executeSignIn(email, password)

        assertTrue(result is Result.Success)
        assertEquals(firebaseUser, (result as Result.Success).data)
    }
}
