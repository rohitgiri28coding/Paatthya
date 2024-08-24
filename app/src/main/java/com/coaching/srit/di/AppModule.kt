package com.coaching.srit.di

import android.app.Application
import android.content.Context
import com.coaching.srit.data.datasource.AuthRepositoryImpl
import com.coaching.srit.data.datasource.NoticeRepositoryImpl
import com.coaching.srit.data.datasource.UserRepositoryImpl
import com.coaching.srit.domain.repository.AuthRepository
import com.coaching.srit.domain.repository.NoticeRepository
import com.coaching.srit.domain.repository.UserRepository
import com.coaching.srit.domain.usecase.AuthDataValidation
import com.coaching.srit.domain.usecase.CreateNoticeUseCase
import com.coaching.srit.domain.usecase.EmailValidator
import com.coaching.srit.domain.usecase.FetchNoticeUseCase
import com.coaching.srit.domain.usecase.NameValidator
import com.coaching.srit.domain.usecase.PasswordValidator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule{
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
    @Provides
    @Singleton
    fun provideApplicationContext(app: Application): Context {
        return app.applicationContext
    }
    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth, context: Context): AuthRepository {
        return AuthRepositoryImpl(auth, context)
    }
    @Provides
    fun provideAuthDataValidation(emailValidator: EmailValidator, passwordValidator: PasswordValidator, nameValidator: NameValidator): AuthDataValidation {
        return AuthDataValidation(emailValidator, passwordValidator, nameValidator)
    }
    @Provides
    fun provideEmailValidator(): EmailValidator {
        return EmailValidator()
    }
    @Provides
    fun providePasswordValidator(): PasswordValidator {
        return PasswordValidator()
    }
    @Provides
    fun provideNameValidator(): NameValidator {
        return NameValidator()
    }
    @Provides
    fun provideUserRepository(firestore: FirebaseFirestore, context: Context): UserRepository {
        return UserRepositoryImpl(firestore, context)
    }
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
    @Provides
    fun provideCreateNoticeUseCase(noticeRepository: NoticeRepository): CreateNoticeUseCase {
        return CreateNoticeUseCase(noticeRepository)
    }
    @Provides
    fun provideNoticeRepository(firestore: FirebaseFirestore): NoticeRepository {
        return NoticeRepositoryImpl(firestore)
    }
    @Provides
    fun provideFetchNoticeUseCase(noticeRepository: NoticeRepository): FetchNoticeUseCase{
        return FetchNoticeUseCase(noticeRepository)
    }
}