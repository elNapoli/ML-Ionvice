package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.storage

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.baldomeronapoli.mlinvoice.presenter.AppState
import com.baldomeronapoli.mlinvoice.presenter.ui.features.history.screens.HistoryFileScreen
import com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.storage.StorageHandleCommands
import com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.storage.StorageViewModel
import com.baldomeronapoli.mlinvoice.presenter.utils.composable
import com.baldomeronapoli.mlinvoice.presenter.utils.sharedViewModel

fun NavGraphBuilder.storageGraph(
    navController: NavHostController,
    appState: AppState
) {

    navigation(
        startDestination = StorageRoute.Index.route,
        route = StorageRoute.route
    ) {
        composable(StorageRoute.Index) {
            val viewModel = it.sharedViewModel<StorageViewModel>(navController)

            StorageHandleCommands(
                navController = navController,
                viewModel = viewModel
            )
            HistoryFileScreen(
                state = viewModel.viewState.collectAsStateWithLifecycle(),
                onIntent = viewModel::setIntent,
            )
        }

    }
}

