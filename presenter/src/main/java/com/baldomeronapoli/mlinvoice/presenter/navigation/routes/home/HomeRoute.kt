package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Home
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.AppBottomRoute
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.Route

data object HomeRoute : Route("home") {
    data object Index : AppBottomRoute("${this.route}/index", "index", Icons.Filled.Home)
    data object Camera : AppBottomRoute("${this.route}/camera", "Camera", Icons.Filled.Camera)
}