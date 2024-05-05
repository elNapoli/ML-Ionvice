package com.baldomeronapoli.mlinvoice.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.Route
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.auth.AuthRoute
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home.HomeRoute
import com.baldomeronapoli.mlinvoice.presenter.observers.AuthObserver
import com.baldomeronapoli.mlinvoice.presenter.state.BaseUiState
import com.baldomeronapoli.mlinvoice.presenter.ui.MainScreen
import com.baldomeronapoli.mlinvoice.presenter.ui.features.auth.AuthViewModel
import com.baldomeronapoli.mlinvoice.presenter.ui.features.core.CoreContract
import com.baldomeronapoli.mlinvoice.presenter.ui.features.core.CoreViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

sealed class Screen {
    data object Loading : Screen()
    data object Error : Screen()
    data class Success(val startDestination: Route) : Screen()
}

@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity(), AuthObserver {
    private val viewModel: CoreViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()
    private var screen: Screen by mutableStateOf(Screen.Loading)
    private lateinit var appState: AppState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeCommand()
        viewModel.addObserver(this)

        setContent {
            appState = rememberAppState()
            observeState(appState)
            viewModel.getCurrentUser()

            when (val currentScreen = screen) {
                is Screen.Loading -> {}
                is Screen.Error -> {}
                is Screen.Success -> MainScreen(
                    appState = appState,
                    startDestination = currentScreen.startDestination
                )
            }
        }

    }

    private fun subscribeCommand() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.command.collect { command ->
                    handleCommand(command)
                }
            }
        }
    }

    private fun handleUserState(appState: AppState, userState: BaseUiState<FirebaseUser?>) {
        when (userState) {
            is BaseUiState.Error -> screen = Screen.Error

            is BaseUiState.Loading -> screen = Screen.Loading

            is BaseUiState.Loaded -> {
                val startDestination = if (userState.data != null) {
                    HomeRoute
                } else {
                    AuthRoute
                }
                screen = Screen.Success(startDestination)

            }

            is BaseUiState.NotStarted -> {

            }
        }
    }

    private fun observeState(appState: AppState) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { state ->
                    handleUserState(appState = appState, state.userState)
                }
            }
        }
    }

    private fun handleCommand(command: CoreContract.Command) {
        when (command) {
            is CoreContract.Command.SetErrorSignIn -> viewModel.setState {
                copy(
                    userState = BaseUiState.Error(command.message),
                )
            }

            is CoreContract.Command.ShowSignInLoading -> viewModel.setState {
                copy(
                    userState = BaseUiState.Loading(true),
                )
            }

            is CoreContract.Command.SaveUser -> {

                viewModel.setState {
                    copy(
                        userState = BaseUiState.Loaded(command.user),
                    )
                }
            }
        }
    }


    override fun onSiIn(user: FirebaseUser?) {
        appState.user.value = user
    }

}