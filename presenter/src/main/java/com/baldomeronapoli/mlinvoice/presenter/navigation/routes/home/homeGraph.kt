package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.baldomeronapoli.mlinvoice.presenter.ui.features.home.screens.HomeScreen
import com.baldomeronapoli.mlinvoice.presenter.utils.composable

fun NavGraphBuilder.homeGraph(
    navController: NavHostController
) {

    navigation(
        startDestination = HomeRoute.Index.route,
        route = HomeRoute.route
    ) {
        composable(HomeRoute.Index) {
            HomeScreen()
        }
    }
}

