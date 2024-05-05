package com.baldomeronapoli.mlinvoice.domain.usecases.auth

import com.baldomeronapoli.mlinvoice.domain.repositories.AuthRepository
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(): Flow<NetworkResult<FirebaseUser?>> =
        repository.getCurrentUser()

}
