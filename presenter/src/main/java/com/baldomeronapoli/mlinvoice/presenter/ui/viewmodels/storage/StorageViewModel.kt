package com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.storage

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.baldomeronapoli.mlinvoice.domain.usecases.cloudStorage.GetFilesUseCase
import com.baldomeronapoli.mlinvoice.domain.usecases.cloudStorage.UploadFileUseCase
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import com.baldomeronapoli.mlinvoice.presenter.base.BaseViewModel
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val uploadFileUseCase: UploadFileUseCase,
    private val getFilesUseCase: GetFilesUseCase
) :
    BaseViewModel<StorageContract.Intent, StorageContract.State, StorageContract.Command>() {
    override fun setInitialState() = StorageContract.State(
        file = BaseUiState.NotStarted(),
        files = BaseUiState.NotStarted()
    )

    override fun handleIntents(intent: StorageContract.Intent) {
        when (intent) {

            is StorageContract.Intent.CapturePhoto -> uploadFile(intent.name, intent.uri)
            is StorageContract.Intent.PickedPhoto -> uploadFile(intent.name, intent.uri)
            StorageContract.Intent.GetAllFiles -> getAllFiles()
        }
    }

    private fun uploadFile(fileName: String, filePath: Uri) = viewModelScope.launch {
        uploadFileUseCase(fileName, filePath).collect { result ->
            when (result) {
                is NetworkResult.Error -> sendCommand { StorageContract.Command.File.Error(result.message!!) }

                is NetworkResult.Loading -> sendCommand { StorageContract.Command.File.Loading }


                is NetworkResult.Success -> sendCommand {
                    StorageContract.Command.File.Success(result.data)
                }
            }
        }
    }


    private fun getAllFiles() = viewModelScope.launch {
        getFilesUseCase().collect { result ->
            when (result) {
                is NetworkResult.Error -> sendCommand { StorageContract.Command.Files.Error(result.message!!) }

                is NetworkResult.Loading -> sendCommand { StorageContract.Command.Files.Loading }


                is NetworkResult.Success -> sendCommand {
                    StorageContract.Command.Files.Success(result.data)
                }
            }
        }
    }

}
