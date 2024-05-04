package com.baldomeronapoli.mlinvoice.domain.usecases.auth

import com.baldomeronapoli.mlinvoice.domain.repositories.AuthRepository
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository,
)  {
     suspend operator fun invoke(
        email: String,
        password:String
    ): Flow<NetworkResult<AuthResult>> =
        repository.signIn(email, password)

}
