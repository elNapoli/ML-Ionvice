package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.baldomeronapoli.mlinvoice.presenter.AppState
import com.baldomeronapoli.mlinvoice.presenter.ui.features.home.screens.HomeScreen
import com.baldomeronapoli.mlinvoice.presenter.utils.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted

@OptIn(ExperimentalPermissionsApi::class)
fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    appState: AppState
) {

    navigation(
        startDestination = HomeRoute.Index.route,
        route = HomeRoute.route
    ) {
        composable(HomeRoute.Index) {
            HomeScreen(
                appState.cameraPermissionState.status.isGranted,
                onRequestPermission = appState.cameraPermissionState::launchPermissionRequest
            )
        }
    }
}

