package com.baldomeronapoli.mlinvoice.presenter.ui.features.core

import com.baldomeronapoli.mlinvoice.presenter.base.ViewCommand
import com.baldomeronapoli.mlinvoice.presenter.base.ViewIntent
import com.baldomeronapoli.mlinvoice.presenter.base.ViewState
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import com.google.firebase.auth.FirebaseUser

class CoreContract {
    sealed class Intent : ViewIntent {
        data object Test : Intent()
    }

    data class State(
        val userState: BaseUiState<FirebaseUser?>,
    ) : ViewState

    sealed class Command : ViewCommand {
        data class SetErrorSignIn(val message: String) : Command()
        data object ShowSignInLoading : Command()
        data class SaveUser(val user: FirebaseUser?, val message: String) : Command()
    }
}