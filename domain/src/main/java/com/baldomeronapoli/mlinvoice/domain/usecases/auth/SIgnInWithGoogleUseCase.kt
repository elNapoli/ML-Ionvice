package com.baldomeronapoli.mlinvoice.domain.usecases.auth

import com.baldomeronapoli.mlinvoice.domain.repositories.AuthRepository
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SIgnInWithGoogleUseCase @Inject constructor(
    private val repository: AuthRepository,
)  {
     suspend operator fun invoke(
       credential: AuthCredential
    ): Flow<NetworkResult<AuthResult>> =
        repository.signInWithGoogle(credential)

}
