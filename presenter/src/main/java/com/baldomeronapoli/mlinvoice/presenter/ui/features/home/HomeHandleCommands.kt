package com.baldomeronapoli.mlinvoice.presenter.ui.features.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home.HomeRoute
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import com.baldomeronapoli.mlinvoice.presenter.utils.collectWithLifecycle

@Composable
fun HomeHandleCommands(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val events = viewModel.command

    events.collectWithLifecycle { event ->
        when (event) {
            HomeContract.Command.Route.GoToCameraScreen -> navController.navigate(HomeRoute.Camera.route)

            HomeContract.Command.Route.GoToBack -> navController.popBackStack()


            is HomeContract.Command.File.Error -> viewModel.setState {
                copy(
                    file = BaseUiState.Error(event.message)
                )
            }

            HomeContract.Command.File.Loading -> viewModel.setState {
                copy(
                    file = BaseUiState.Loading(true)
                )
            }

            is HomeContract.Command.File.Success -> viewModel.setState {
                copy(
                    file = BaseUiState.Loaded(event.path)
                )
            }
        }
    }

}