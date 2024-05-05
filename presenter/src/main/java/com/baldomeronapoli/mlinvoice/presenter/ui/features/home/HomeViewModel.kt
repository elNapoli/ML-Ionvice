package com.baldomeronapoli.mlinvoice.presenter.ui.features.home

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.baldomeronapoli.mlinvoice.domain.usecases.home.SavePhotoToGalleryUseCase
import com.baldomeronapoli.mlinvoice.presenter.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val savePhotoToGalleryUseCase: SavePhotoToGalleryUseCase) :
    BaseViewModel<HomeContract.Intent, HomeContract.State, HomeContract.Command>() {
    override fun setInitialState() = HomeContract.State(
        capturedImage = null,
    )

    override fun handleIntents(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.GoToCameraScreen -> sendCommand { HomeContract.Command.GoToCameraScreen }
            is HomeContract.Intent.StorePhotoInGallery -> {
                storePhotoInGallery(intent.bitmap)
            }
        }
    }

    private fun storePhotoInGallery(bitmap: Bitmap) {
        viewModelScope.launch {
            savePhotoToGalleryUseCase.invoke(bitmap)
            sendCommand { HomeContract.Command.UpdateCapturedPhoto(bitmap) }
        }
    }


}
