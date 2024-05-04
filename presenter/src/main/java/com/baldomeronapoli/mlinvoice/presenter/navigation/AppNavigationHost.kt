package com.baldomeronapoli.mlinvoice.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home.HomeRoute
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home.homeGraph


@Composable
fun AppNavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController, startDestination = HomeRoute.route
    ) {
        homeGraph(navController)
    }
}