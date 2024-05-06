package com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.auth

import androidx.lifecycle.viewModelScope
import com.baldomeronapoli.mlinvoice.domain.usecases.auth.SignInUseCase
import com.baldomeronapoli.mlinvoice.domain.usecases.auth.SignUpWithPasswordUseCase
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import com.baldomeronapoli.mlinvoice.presenter.base.BaseViewModel
import com.baldomeronapoli.mlinvoice.presenter.observers.AuthObserver
import com.baldomeronapoli.mlinvoice.presenter.ui.states.initAuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpWithPasswordUseCase: SignUpWithPasswordUseCase,
) : BaseViewModel<AuthContract.Intent, AuthContract.State, AuthContract.Command>() {
    override fun setInitialState() = initAuthState()
    private val observers = mutableListOf<AuthObserver>()


    override fun handleIntents(intent: AuthContract.Intent) {
        when (intent) {
            AuthContract.Intent.SignIn -> {
                val credential = viewState.value.credential
                signIn(credential.email, credential.password)
            }

            is AuthContract.Intent.OnUpdateEmail -> sendCommand {
                AuthContract.Command.UpdateEmail(
                    intent.value
                )
            }

            is AuthContract.Intent.OnUpdatePassword -> sendCommand {
                AuthContract.Command.UpdatePassword(
                    intent.value
                )
            }

            AuthContract.Intent.SignIn -> TODO()
            AuthContract.Intent.TogglePasswordVisibility -> sendCommand {
                AuthContract.Command.UpdatePasswordVisibility
            }

            AuthContract.Intent.GoToSignUp -> sendCommand {
                AuthContract.Command.Route.GoToSignUp
            }

            AuthContract.Intent.GoToBack -> sendCommand {
                AuthContract.Command.Route.GoToBack
            }

            AuthContract.Intent.SignUp -> {
                val credential = viewState.value.credential
                signUp(credential.email, credential.password)
            }
        }
    }

    private fun signIn(email: String, password: String) = viewModelScope.launch {
        signInUseCase(email, password).collect { result ->
            when (result) {
                is NetworkResult.Error -> sendCommand {
                    AuthContract.Command.SetErrorSignIn(
                        result.exception
                    )
                }

                is NetworkResult.Loading -> sendCommand { AuthContract.Command.ShowSignInLoading }
                is NetworkResult.Success -> {

                    sendCommand {
                        AuthContract.Command.SaveUser(
                            result.data.user,
                            ""
                        )
                    }
                }

            }
        }
    }


    private fun signUp(email: String, password: String) = viewModelScope.launch {
        signUpWithPasswordUseCase(email, password).collect { result ->
            when (result) {
                is NetworkResult.Error -> sendCommand {
                    AuthContract.Command.SetErrorSignIn(
                        result.exception
                    )
                }

                is NetworkResult.Loading -> sendCommand { AuthContract.Command.ShowSignInLoading }
                is NetworkResult.Success -> {
                    sendCommand {
                        AuthContract.Command.SaveUser(
                            null,
                            "El usuario se ha creado con exito"
                        )
                    }
                    sendCommand {
                        AuthContract.Command.Route.GoToBack
                    }
                }

            }
        }
    }
}
