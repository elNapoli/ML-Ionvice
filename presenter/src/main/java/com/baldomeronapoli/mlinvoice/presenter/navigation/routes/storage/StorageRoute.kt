package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.storage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Storage
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.AppBottomRoute
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.Route

data object StorageRoute : Route("storage") {
    data object Index : AppBottomRoute("${this.route}/index", "Storage", Icons.Filled.Storage)
}