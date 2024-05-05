package com.baldomeronapoli.mlinvoice.presenter.ui.features.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home.HomeRoute
import com.baldomeronapoli.mlinvoice.presenter.utils.collectWithLifecycle

@Composable
fun HomeHandleCommands(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val events = viewModel.command

    events.collectWithLifecycle { event ->
        when (event) {
            HomeContract.Command.GoToCameraScreen -> navController.navigate(HomeRoute.Camera.route)
            is HomeContract.Command.UpdateCapturedPhoto -> viewModel.setState {
                copy(
                    capturedImage = event.bitmap
                )
            }
        }
    }

}