package com.baldomeronapoli.mlinvoice.data.repositories

import com.baldomeronapoli.mlinvoice.data.utils.toSecureFlow
import com.baldomeronapoli.mlinvoice.domain.repositories.AuthRepository
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun signIn(email: String, password: String): Flow<NetworkResult<AuthResult>> =
        toSecureFlow {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        }

    override suspend fun createUserWithPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<AuthResult>> = toSecureFlow {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        FirebaseAuth.getInstance().signOut()
        result
    }

    override suspend fun signInWithGoogle(credential: AuthCredential): Flow<NetworkResult<AuthResult>> =
        toSecureFlow {
            firebaseAuth.signInWithCredential(credential).await()
        }

    override suspend fun getCurrentUser(): Flow<NetworkResult<FirebaseUser?>> =
        toSecureFlow {
            firebaseAuth.currentUser
        }


}