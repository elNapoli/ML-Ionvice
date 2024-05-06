package com.baldomeronapoli.mlinvoice.presenter.ui.states

import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import com.baldomeronapoli.mlinvoice.presenter.ui.viewmodels.auth.AuthContract


fun initAuthState() = AuthContract.State(
    userState = BaseUiState.NotStarted(),
    credential = Credential("", "", false),
)