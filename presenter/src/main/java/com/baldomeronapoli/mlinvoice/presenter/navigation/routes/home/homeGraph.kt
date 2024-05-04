package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.baldomeronapoli.mlinvoice.presenter.utils.composable

fun NavGraphBuilder.homeGraph(
    navController: NavHostController
) {

    navigation(
        startDestination = HomeRoute.Index.route,
        route = HomeRoute.route
    ) {
        composable(HomeRoute.Index) {
            Text(text = "hola")
        }
    }
}

