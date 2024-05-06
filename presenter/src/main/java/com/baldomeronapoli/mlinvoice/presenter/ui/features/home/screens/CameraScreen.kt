package com.baldomeronapoli.mlinvoice.presenter.ui.features.home.screens

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date


fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
}