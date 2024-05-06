package com.baldomeronapoli.mlinvoice.presenter.ui.features.home

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.baldomeronapoli.mlinvoice.domain.usecases.cloudStorage.UploadFileUseCase
import com.baldomeronapoli.mlinvoice.domain.usecases.home.SavePhotoToGalleryUseCase
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import com.baldomeronapoli.mlinvoice.presenter.base.BaseViewModel
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savePhotoToGalleryUseCase: SavePhotoToGalleryUseCase,
    private val uploadFileUseCase: UploadFileUseCase,
) :
    BaseViewModel<HomeContract.Intent, HomeContract.State, HomeContract.Command>() {
    override fun setInitialState() = HomeContract.State(
        file = BaseUiState.NotStarted()
    )

    override fun handleIntents(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.GoToCameraScreen -> sendCommand { HomeContract.Command.Route.GoToCameraScreen }

            is HomeContract.Intent.CapturePhoto -> uploadFile(intent.name, intent.uri)
            is HomeContract.Intent.PickedPhoto -> uploadFile(intent.name, intent.uri)
        }
    }

    private fun uploadFile(fileName: String, filePath: Uri) = viewModelScope.launch {
        uploadFileUseCase(fileName, filePath).collect { result ->
            when (result) {
                is NetworkResult.Error -> sendCommand { HomeContract.Command.File.Error(result.message!!) }

                is NetworkResult.Loading -> sendCommand { HomeContract.Command.File.Loading }


                is NetworkResult.Success -> sendCommand {
                    HomeContract.Command.File.Success(result.data)
                }
            }
        }
    }

    private fun storePhotoInGallery(bitmap: Bitmap) = viewModelScope.launch {
        savePhotoToGalleryUseCase(bitmap).collect { result ->
            when (result) {
                is NetworkResult.Error -> {

                }

                is NetworkResult.Loading -> {
                }


                is NetworkResult.Success -> result.data?.let {

                }
            }
        }
    }


}
