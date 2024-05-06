package com.baldomeronapoli.mlinvoice.domain.usecases.home

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.OutputStream
import javax.inject.Inject


class SavePhotoToGalleryUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    suspend operator fun invoke(
        capturePhotoBitmap: Bitmap
    ): Flow<NetworkResult<Uri?>> = flow {
        emit(NetworkResult.Loading(true))

        try {
            val resolver: ContentResolver = context.applicationContext.contentResolver
            val imageCollection: Uri = when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> MediaStore.Images.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL_PRIMARY
                )

                else -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }

            val nowTimestamp: Long = System.currentTimeMillis()
            val imageContentValues: ContentValues = ContentValues().apply {

                put(MediaStore.Images.Media.DISPLAY_NAME, "Your image name" + ".jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    put(MediaStore.MediaColumns.DATE_TAKEN, nowTimestamp)
                    put(
                        MediaStore.MediaColumns.RELATIVE_PATH,
                        Environment.DIRECTORY_DCIM + "/YourAppNameOrAnyOtherSubFolderName"
                    )
                    put(MediaStore.MediaColumns.IS_PENDING, 1)
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    put(MediaStore.Images.Media.DATE_TAKEN, nowTimestamp)
                    put(MediaStore.Images.Media.DATE_ADDED, nowTimestamp)
                    put(MediaStore.Images.Media.DATE_MODIFIED, nowTimestamp)
                    put(MediaStore.Images.Media.AUTHOR, "Your Name")
                    put(MediaStore.Images.Media.DESCRIPTION, "Your description")
                }
            }

            val imageMediaStoreUri: Uri? = resolver.insert(imageCollection, imageContentValues)

            val result: NetworkResult<Uri?> = imageMediaStoreUri?.let { uri ->
                kotlin.runCatching {
                    resolver.openOutputStream(uri).use { outputStream: OutputStream? ->
                        checkNotNull(outputStream) { "Couldn't create file for gallery, MediaStore output stream is null" }
                        capturePhotoBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        imageContentValues.clear()
                        imageContentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                        resolver.update(uri, imageContentValues, null, null)
                    }
                    NetworkResult.Success(imageMediaStoreUri)
                }.getOrElse { exception: Throwable ->
                    resolver.delete(uri, null, null)
                    NetworkResult.Error(exception = exception.message!!)
                }
            } ?: run {
                NetworkResult.Error(exception = "Couldn't create file for gallery")
            }
            emit(result)
        } catch (e: Exception) {
            emit(NetworkResult.Error(exception = e.message!!))
        }
    }

}
