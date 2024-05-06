package com.baldomeronapoli.mlinvoice.presenter.ui.features.history.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.baldomeronapoli.mlinvoice.presenter.components.LoadingDialog
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.storage.StorageContract

@Composable
fun HistoryFileScreen(
    state: State<StorageContract.State>,
    onIntent: (event: StorageContract.Intent) -> Unit,
) {
    LaunchedEffect(Unit) {
        onIntent(StorageContract.Intent.GetAllFiles)
    }
    LoadingDialog(state.value.files is BaseUiState.Loading)
    state.value.files.data?.let { list ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(list.size) { index ->
                val imageUrl = list[index]
                CoilImage(
                    imageUrl = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun CoilImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest
            .Builder(LocalContext.current)
            .data(data = imageUrl)
            .apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
                transformations(
                    RoundedCornersTransformation(
                        topLeft = 20f,
                        topRight = 20f,
                        bottomLeft = 20f,
                        bottomRight = 20f
                    )
                )
            })
            .build()
    )

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.padding(6.dp),
        contentScale = contentScale,
    )
}