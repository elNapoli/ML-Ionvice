package com.baldomeronapoli.mlinvoice.domain.repositories

import android.net.Uri
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    suspend fun uploadFile(fileName: String, filePath: Uri): Flow<NetworkResult<String?>>
    suspend fun getUserFiles(): Flow<NetworkResult<List<String>>>
}