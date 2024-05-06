package com.baldomeronapoli.mlinvoice.domain.usecases.cloudStorage

import android.net.Uri
import com.baldomeronapoli.mlinvoice.domain.repositories.StorageRepository
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UploadFileUseCase @Inject constructor(
    private val repository: StorageRepository,
) {
    suspend operator fun invoke(fileName: String, filePath: Uri): Flow<NetworkResult<String?>> =
        repository.uploadFile(fileName, filePath)

}
