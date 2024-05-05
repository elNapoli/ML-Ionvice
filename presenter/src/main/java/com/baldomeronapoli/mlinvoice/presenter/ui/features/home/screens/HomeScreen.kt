package com.baldomeronapoli.mlinvoice.presenter.ui.features.home.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PermMedia
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.mlinvoice.presenter.R
import com.baldomeronapoli.mlinvoice.presenter.ui.features.home.HomeContract

@Composable
fun HomeScreen(
    hasPermission: Boolean,
    onRequestPermission: () -> Unit,
    state: State<HomeContract.State>,
    onIntent: (event: HomeContract.Intent) -> Unit,
) {
    if (hasPermission) {
        HomeContent(onIntent)
    } else {
        NoPermissionScreen(onRequestPermission)
    }

}

@Composable
fun NoPermissionScreen(onRequestPermission: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.camera_permission),
            )
            Button(onClick = onRequestPermission) {
                Icon(imageVector = Icons.Default.Camera, contentDescription = "Camera")
                Text(text = "Dar permiso")
            }
        }
    }
}

@Composable
fun HomeContent(onIntent: (event: HomeContract.Intent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Card(
            modifier = Modifier.size(200.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            shape = MaterialTheme.shapes.small,
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.PermMedia,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
                Text(text = stringResource(id = R.string.gallery))
            }

        }
        Card(
            modifier = Modifier
                .size(200.dp)
                .clickable { onIntent(HomeContract.Intent.GoToCameraScreen) },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
            shape = MaterialTheme.shapes.small,
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.CameraAlt,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
                Text(text = stringResource(id = R.string.Camera))

            }
        }
    }
}