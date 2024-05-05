package com.baldomeronapoli.mlinvoice.presenter.ui.features.home

import android.graphics.Bitmap
import com.baldomeronapoli.mlinvoice.presenter.base.ViewCommand
import com.baldomeronapoli.mlinvoice.presenter.base.ViewIntent
import com.baldomeronapoli.mlinvoice.presenter.base.ViewState

class HomeContract {
    sealed class Intent : ViewIntent {
        data object GoToCameraScreen : Intent()
        data class StorePhotoInGallery(val bitmap: Bitmap) : Intent()

    }

    data class State(
        val capturedImage: Bitmap?,
    ) : ViewState

    sealed class Command : ViewCommand {
        data object GoToCameraScreen : Command()
        data class UpdateCapturedPhoto(val bitmap: Bitmap) : Command()
    }
}