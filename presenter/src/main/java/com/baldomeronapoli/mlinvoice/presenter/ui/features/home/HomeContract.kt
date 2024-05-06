package com.baldomeronapoli.mlinvoice.presenter.ui.features.home

import android.net.Uri
import com.baldomeronapoli.mlinvoice.presenter.base.ViewCommand
import com.baldomeronapoli.mlinvoice.presenter.base.ViewIntent
import com.baldomeronapoli.mlinvoice.presenter.base.ViewState
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState

class HomeContract {
    sealed class Intent : ViewIntent {
        data object GoToCameraScreen : Intent()
        data class CapturePhoto(val name: String, val uri: Uri) : Intent()
        data class PickedPhoto(val name: String, val uri: Uri) : Intent()

    }

    data class State(
        val file: BaseUiState<String?>
    ) : ViewState

    sealed class Command : ViewCommand {
        sealed class File : Command() {
            data object Loading : File()
            data class Error(val message: String) : File()
            data class Success(val path: String?) : File()
        }

        sealed class Route : Command() {
            data object GoToCameraScreen : Route()
            data object GoToBack : Route()
        }

    }
}