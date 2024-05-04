package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.auth

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.AppBottomRoute
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.Route


data object AuthRoute : Route("auth") {
    data object SignIn : AppBottomRoute("${this.route}/sign-in", "SignIn", Icons.Filled.Home)
    data object SignUp : AppBottomRoute("${this.route}/sign-up", "SignUp", Icons.Filled.Home)
}