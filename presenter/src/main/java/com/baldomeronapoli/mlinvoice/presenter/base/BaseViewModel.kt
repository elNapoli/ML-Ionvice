package com.baldomeronapoli.mlinvoice.presenter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<Intent : ViewIntent, State : ViewState, Command : ViewCommand> :
    ViewModel() {

    protected abstract fun setInitialState(): State
    abstract fun handleIntents(intent: Intent)

    private val initialState: State by lazy { setInitialState() }

    private val _viewState = MutableStateFlow(initialState)
    val viewState: StateFlow<State> = _viewState.asStateFlow()

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()

    private val _command: Channel<Command> = Channel()
    val command = _command.receiveAsFlow()

    init {
        subscribeToIntents()
    }

    private fun subscribeToIntents() {
        viewModelScope.launch {
            _intent.collect {
                handleIntents(it)
            }
        }
    }

    fun setIntent(intent: Intent) {
        viewModelScope.launch { _intent.emit(intent) }
    }

    fun setState(reducer: State.() -> State) {
        _viewState.value = viewState.value.reducer()
    }

    protected fun sendCommand(builder: () -> Command) {
        viewModelScope.launch { _command.send(builder()) }
    }

}