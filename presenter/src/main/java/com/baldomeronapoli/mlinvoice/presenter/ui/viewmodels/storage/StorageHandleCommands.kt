package com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.storage

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home.HomeRoute
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import com.baldomeronapoli.mlinvoice.presenter.utils.collectWithLifecycle

@Composable
fun StorageHandleCommands(
    navController: NavHostController,
    viewModel: StorageViewModel
) {
    val events = viewModel.command

    events.collectWithLifecycle { event ->
        when (event) {
            StorageContract.Command.Route.GoToCameraScreen -> navController.navigate(HomeRoute.Camera.route)

            StorageContract.Command.Route.GoToBack -> navController.popBackStack()


            is StorageContract.Command.File.Error -> viewModel.setState {
                copy(
                    file = BaseUiState.Error(event.message)
                )
            }

            StorageContract.Command.File.Loading -> viewModel.setState {
                copy(
                    file = BaseUiState.Loading(true)
                )
            }

            is StorageContract.Command.File.Success -> viewModel.setState {
                copy(
                    file = BaseUiState.Loaded(event.path)
                )
            }

            is StorageContract.Command.Files.Error -> viewModel.setState {
                copy(
                    files = BaseUiState.Error(event.message)
                )
            }

            StorageContract.Command.Files.Loading -> viewModel.setState {
                copy(
                    files = BaseUiState.Loading(true)
                )
            }

            is StorageContract.Command.Files.Success -> viewModel.setState {
                copy(
                    files = BaseUiState.Loaded(event.files)
                )
            }
        }
    }

}