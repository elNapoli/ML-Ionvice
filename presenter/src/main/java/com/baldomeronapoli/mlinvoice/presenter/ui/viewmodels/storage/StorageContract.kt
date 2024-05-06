package com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.storage

import android.net.Uri
import com.baldomeronapoli.mlinvoice.presenter.base.ViewCommand
import com.baldomeronapoli.mlinvoice.presenter.base.ViewIntent
import com.baldomeronapoli.mlinvoice.presenter.base.ViewState
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState

class StorageContract {
    sealed class Intent : ViewIntent {
        data object GetAllFiles : Intent()
        data class CapturePhoto(val name: String, val uri: Uri) : Intent()
        data class PickedPhoto(val name: String, val uri: Uri) : Intent()

    }

    data class State(
        val file: BaseUiState<String?>,
        val files: BaseUiState<List<String>>
    ) : ViewState

    sealed class Command : ViewCommand {
        sealed class File : Command() {
            data object Loading : File()
            data class Error(val message: String) : File()
            data class Success(val path: String?) : File()
        }

        sealed class Files : Command() {
            data object Loading : File()
            data class Error(val message: String) : File()
            data class Success(val files: List<String>) : File()
        }

        sealed class Route : Command() {
            data object GoToCameraScreen : Route()
            data object GoToBack : Route()
        }

    }
}