package com.baldomeronapoli.mlinvoice.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.Route
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.auth.authGraph
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home.homeGraph


@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Route
) {
    NavHost(
        modifier = modifier,
        navController = navController, startDestination = startDestination.route
    ) {
        authGraph(navController)
        homeGraph(navController)
    }
}