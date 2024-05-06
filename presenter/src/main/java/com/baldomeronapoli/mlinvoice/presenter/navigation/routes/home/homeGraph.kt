package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.baldomeronapoli.mlinvoice.presenter.AppState
import com.baldomeronapoli.mlinvoice.presenter.ui.features.home.HomeHandleCommands
import com.baldomeronapoli.mlinvoice.presenter.ui.features.home.HomeViewModel
import com.baldomeronapoli.mlinvoice.presenter.ui.features.home.screens.HomeScreen
import com.baldomeronapoli.mlinvoice.presenter.utils.composable
import com.baldomeronapoli.mlinvoice.presenter.utils.sharedViewModel
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
            val viewModel = it.sharedViewModel<HomeViewModel>(navController)

            HomeHandleCommands(
                navController = navController,
                viewModel = viewModel
            )
            HomeScreen(
                state = viewModel.viewState.collectAsStateWithLifecycle(),
                onIntent = viewModel::setIntent,
            )
        }
        composable(HomeRoute.Camera) {
            val viewModel = it.sharedViewModel<HomeViewModel>(navController)

            HomeHandleCommands(
                navController = navController,
                viewModel = viewModel
            )

        }
    }
}

