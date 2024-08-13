package com.coaching.srit.di

import android.app.Application
import android.content.Context
import com.coaching.srit.data.datasource.AuthRepositoryImpl
import com.coaching.srit.domain.repository.AuthRepository
import com.coaching.srit.domain.usecase.AuthDataValidation
import com.coaching.srit.domain.usecase.EmailValidator
import com.coaching.srit.domain.usecase.PasswordValidator
import com.google.firebase.auth.FirebaseAuth
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
    fun provideAuthDataValidation(emailValidator: EmailValidator, passwordValidator: PasswordValidator): AuthDataValidation {
        return AuthDataValidation(emailValidator, passwordValidator)
    }
    @Provides
    fun provideEmailValidator(): EmailValidator {
        return EmailValidator()
    }
    @Provides
    fun providePasswordValidator(): PasswordValidator {
        return PasswordValidator()
    }
}