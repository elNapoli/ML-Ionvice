package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.baldomeronapoli.mlinvoice.presenter.AppState
import com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.storage.StorageHandleCommands
import com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.storage.StorageViewModel
import com.baldomeronapoli.mlinvoice.presenter.ui.features.home.screens.HomeScreen
import com.baldomeronapoli.mlinvoice.presenter.utils.composable
import com.baldomeronapoli.mlinvoice.presenter.utils.sharedViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi

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
            val viewModel = it.sharedViewModel<StorageViewModel>(navController)

            StorageHandleCommands(
                navController = navController,
                viewModel = viewModel
            )
            HomeScreen(
                state = viewModel.viewState.collectAsStateWithLifecycle(),
                onIntent = viewModel::setIntent,
            )
        }
        composable(HomeRoute.Camera) {
            val viewModel = it.sharedViewModel<StorageViewModel>(navController)

            StorageHandleCommands(
                navController = navController,
                viewModel = viewModel
            )

        }
    }
}

