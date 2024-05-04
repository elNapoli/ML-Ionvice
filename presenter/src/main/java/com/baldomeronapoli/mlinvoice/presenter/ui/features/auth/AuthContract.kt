package com.baldomeronapoli.mlinvoice.presenter.ui.features.auth

import com.baldomeronapoli.mlinvoice.presenter.base.ViewCommand
import com.baldomeronapoli.mlinvoice.presenter.base.ViewIntent
import com.baldomeronapoli.mlinvoice.presenter.base.ViewState
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import com.baldomeronapoli.mlinvoice.presenter.ui.features.states.Credential
import com.google.firebase.auth.FirebaseUser

class AuthContract {
    sealed class Intent : ViewIntent {
        data class OnUpdatePassword(val value: String) : Intent()
        data class OnUpdateEmail(val value: String) : Intent()
        data object TogglePasswordVisibility : Intent()
        data object SignIn : Intent()
        data object SignUp : Intent()
        data object GoToSignUp : Intent()
        data object GoToBack : Intent()

    }

    data class State(
        val userState: BaseUiState<FirebaseUser>,
        val credential: Credential,
    ) : ViewState

    sealed class Command : ViewCommand {
        data class SetErrorSignIn(val message: String) : Command()
        data object CleanInitialState : Command()
        data object ShowSignInLoading : Command()
        data class SaveUser(val user: FirebaseUser, val message: String) : Command()
        data class UpdatePassword(val password: String) : Command()
        data class UpdateEmail(val email: String) : Command()
        data object UpdatePasswordVisibility : Command()
        sealed class Route : Command() {
            data object GoToSignUp : Route()
            data object GoToBack : Route()
        }
    }
}