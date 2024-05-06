package com.baldomeronapoli.mlinvoice.data.repositories

import android.net.Uri
import com.baldomeronapoli.mlinvoice.data.services.CloudStorageManager
import com.baldomeronapoli.mlinvoice.data.utils.toSecureFlow
import com.baldomeronapoli.mlinvoice.domain.repositories.StorageRepository
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val cloudStorageManager: CloudStorageManager
) : StorageRepository {
    override suspend fun uploadFile(
        fileName: String,
        filePath: Uri
    ): Flow<NetworkResult<String?>> = toSecureFlow {
        cloudStorageManager.uploadFile(fileName, filePath)
    }

    override suspend fun getUserFiles(): Flow<NetworkResult<List<String>>> = toSecureFlow {
        cloudStorageManager.getUserImages()
    }
}