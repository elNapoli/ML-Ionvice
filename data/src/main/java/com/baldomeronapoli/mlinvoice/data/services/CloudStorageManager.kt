package com.baldomeronapoli.mlinvoice.data.services

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CloudStorageManager @Inject constructor(
    storage: FirebaseStorage,
    authManager: AuthManager
) {
    private val storageRef = storage.reference
    private val userId = authManager.getCurrentUser()?.uid

    private fun getStorageReference(): StorageReference {
        return storageRef.child(userId ?: "").child("photos")
    }

    suspend fun uploadFile(fileName: String, filePath: Uri): String? {
        return try {
            val fileRef = getStorageReference().child(fileName)
            val uploadTask = fileRef.putFile(filePath)
            uploadTask.await()
            fileRef.downloadUrl.await().toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getUserImages(): List<String> {
        val imageUrls = mutableListOf<String>()
        val listResult: ListResult = getStorageReference().listAll().await()
        for (item in listResult.items) {
            val url = item.downloadUrl.await().toString()
            imageUrls.add(url)
        }
        return imageUrls
    }
}