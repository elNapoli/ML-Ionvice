package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.navigation.NavType
import com.baldomeronapoli.mlinvoice.presenter.navigation.NavArg
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.AppBottomRoute
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.Route


data object HomeRoute : Route("home") {
    data object Index : AppBottomRoute("${this.route}/index", "Home", Icons.Filled.Home)
}