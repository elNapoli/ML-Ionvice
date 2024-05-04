package com.baldomeronapoli.mlinvoice.presenter.ui.features.states

import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import com.baldomeronapoli.mlinvoice.presenter.ui.features.auth.AuthContract


fun initAuthState() = AuthContract.State(
    userState = BaseUiState.NotStarted(),
    credential = Credential("", "", false),
)