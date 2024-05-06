package com.baldomeronapoli.mlinvoice.domain.usecases.cloudStorage

import com.baldomeronapoli.mlinvoice.domain.repositories.StorageRepository
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFilesUseCase @Inject constructor(
    private val repository: StorageRepository,
) {
    suspend operator fun invoke(): Flow<NetworkResult<List<String>>> =
        repository.getUserFiles()

}
