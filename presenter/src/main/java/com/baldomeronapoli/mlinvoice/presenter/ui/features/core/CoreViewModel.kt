package com.baldomeronapoli.mlinvoice.presenter.ui.features.core

import androidx.lifecycle.viewModelScope
import com.baldomeronapoli.mlinvoice.domain.usecases.auth.GetCurrentUserUseCase
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import com.baldomeronapoli.mlinvoice.presenter.base.BaseViewModel
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : BaseViewModel<CoreContract.Intent, CoreContract.State, CoreContract.Command>() {

    init {
        getCurrentUser()
    }

    override fun setInitialState() = CoreContract.State(
        userState = BaseUiState.NotStarted(),
    )

    override fun handleIntents(intent: CoreContract.Intent) {
        when (intent) {
            CoreContract.Intent.Test -> {
                TODO()
                // TODO: No se si aplica usar intent en este coreViewModel
            }
        }
    }

    private fun getCurrentUser() = viewModelScope.launch {
        getCurrentUserUseCase().collect { result ->
            when (result) {
                is NetworkResult.Error -> sendCommand {
                    CoreContract.Command.SetErrorSignIn(
                        result.exception
                    )
                }

                is NetworkResult.Loading -> sendCommand { CoreContract.Command.ShowSignInLoading }
                is NetworkResult.Success -> sendCommand {
                    CoreContract.Command.SaveUser(
                        result.data,
                        ""
                    )
                }

            }
        }
    }

}
