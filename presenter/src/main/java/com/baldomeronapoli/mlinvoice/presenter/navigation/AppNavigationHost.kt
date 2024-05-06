package com.baldomeronapoli.mlinvoice.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.baldomeronapoli.mlinvoice.presenter.AppState
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.Route
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.auth.authGraph
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home.homeGraph
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.storage.storageGraph


@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    startDestination: Route,
    appState: AppState
) {
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController, startDestination = startDestination.route
    ) {
        authGraph(navController)
        homeGraph(
            navController,
            appState
        )
        storageGraph(
            navController,
            appState
        )
    }
}