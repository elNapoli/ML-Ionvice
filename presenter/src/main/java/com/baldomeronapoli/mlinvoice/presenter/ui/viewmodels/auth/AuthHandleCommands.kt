package com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.auth.AuthRoute
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import com.baldomeronapoli.mlinvoice.presenter.ui.states.Credential
import com.baldomeronapoli.mlinvoice.presenter.ui.states.initAuthState
import com.baldomeronapoli.mlinvoice.presenter.utils.ValidateState
import com.baldomeronapoli.mlinvoice.presenter.utils.collectWithLifecycle

@Composable
fun AuthHandleCommands(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    val events = viewModel.command

    events.collectWithLifecycle { event ->
        when (event) {
            is AuthContract.Command.SaveUser -> viewModel.setState {
                copy(
                    userState = BaseUiState.Loaded(event.user, event.message),
                )
            }

            is AuthContract.Command.SetErrorSignIn -> viewModel.setState {
                copy(
                    userState = BaseUiState.Error(event.message),
                )
            }

            AuthContract.Command.ShowSignInLoading -> viewModel.setState {
                copy(
                    userState = BaseUiState.Loading(true),
                )
            }

            is AuthContract.Command.UpdateEmail -> {
                val stateValidator = ValidateState(Credential::class)
                val error = stateValidator.validate(viewModel.viewState.value.credential)
                viewModel.setState {
                    copy(
                        credential = credential.copy(email = event.email, error = error)
                    )
                }
            }

            is AuthContract.Command.UpdatePassword -> viewModel.setState {
                copy(
                    credential = credential.copy(password = event.password)
                )
            }

            AuthContract.Command.UpdatePasswordVisibility -> viewModel.setState {
                copy(
                    credential = credential.copy(passwordVisibility = !credential.passwordVisibility)
                )
            }

            AuthContract.Command.Route.GoToSignUp -> {
                viewModel.setState {
                    initAuthState()
                }
                navController.navigate(
                    AuthRoute.SignUp.route
                )
            }

            AuthContract.Command.Route.GoToBack -> {
                viewModel.setState {
                    copy(
                        credential = Credential("", "", false)
                    )
                }
                navController.popBackStack()
            }

            AuthContract.Command.CleanInitialState -> viewModel.setState {
                initAuthState()
            }
        }
    }

}