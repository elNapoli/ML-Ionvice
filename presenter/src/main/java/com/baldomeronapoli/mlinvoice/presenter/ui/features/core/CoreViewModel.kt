package com.baldomeronapoli.mlinvoice.presenter.ui.features.core

import androidx.lifecycle.viewModelScope
import com.baldomeronapoli.mlinvoice.domain.usecases.auth.GetCurrentUserUseCase
import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import com.baldomeronapoli.mlinvoice.presenter.base.BaseViewModel
import com.baldomeronapoli.mlinvoice.presenter.observers.AuthObserver
import com.baldomeronapoli.mlinvoice.presenter.observers.Observable
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : BaseViewModel<CoreContract.Intent, CoreContract.State, CoreContract.Command>(), Observable {
    private val observers = mutableListOf<AuthObserver>()

    override fun addObserver(observer: AuthObserver) {
        observers.add(observer)
    }

    override fun removeObserver(observer: AuthObserver) {
        observers.remove(observer)
    }

    override fun notifyObservers(action: (AuthObserver) -> Unit) {
        observers.forEach(action)
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

     fun getCurrentUser() = viewModelScope.launch {
        getCurrentUserUseCase().collect { result ->
            when (result) {
                is NetworkResult.Error -> sendCommand {
                    CoreContract.Command.SetErrorSignIn(
                        result.exception
                    )
                }

                is NetworkResult.Loading -> sendCommand { CoreContract.Command.ShowSignInLoading }
                is NetworkResult.Success -> {
                    notifyObservers {
                        it.onSiIn(result.data)
                    }
                    sendCommand {
                        CoreContract.Command.SaveUser(
                            result.data,
                            ""
                        )
                    }
                }

            }
        }
    }


}
