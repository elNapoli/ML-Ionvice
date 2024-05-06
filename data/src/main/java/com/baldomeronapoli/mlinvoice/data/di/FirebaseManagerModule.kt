package com.baldomeronapoli.mlinvoice.data.di

import com.baldomeronapoli.mlinvoice.data.services.AuthManager
import com.baldomeronapoli.mlinvoice.data.services.CloudStorageManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseManagerModule {
    @Provides
    @Singleton
    fun provideAuthManager(firebaseAuth: FirebaseAuth): AuthManager =
        AuthManager(firebaseAuth)

    @Provides
    @Singleton
    fun provideCloudStorageManager(
        storage: FirebaseStorage,
        authManager: AuthManager
    ): CloudStorageManager =
        CloudStorageManager(storage, authManager)
}