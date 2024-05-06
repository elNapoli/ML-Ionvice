package com.baldomeronapoli.mlinvoice.data.di

import com.baldomeronapoli.mlinvoice.data.repositories.AuthRepositoryImpl
import com.baldomeronapoli.mlinvoice.data.repositories.StorageRepositoryImpl
import com.baldomeronapoli.mlinvoice.data.services.AuthManager
import com.baldomeronapoli.mlinvoice.data.services.CloudStorageManager
import com.baldomeronapoli.mlinvoice.domain.repositories.AuthRepository
import com.baldomeronapoli.mlinvoice.domain.repositories.StorageRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        authManager: AuthManager
    ): AuthRepository =
        AuthRepositoryImpl(firebaseAuth, authManager)

    @Provides
    @Singleton
    fun provideStorageRepository(
        cloudStorageManager: CloudStorageManager
    ): StorageRepository =
        StorageRepositoryImpl(cloudStorageManager)
}