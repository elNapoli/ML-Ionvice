package com.baldomeronapoli.mlinvoice.domain.repositories

import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Flow<NetworkResult<AuthResult>>
    suspend fun createUserWithPassword(email: String, password: String): Flow<NetworkResult<AuthResult>>
    suspend fun signInWithGoogle(credential: AuthCredential):Flow<NetworkResult<AuthResult>>
    suspend fun getCurrentUser():Flow<NetworkResult<FirebaseUser?>>
}