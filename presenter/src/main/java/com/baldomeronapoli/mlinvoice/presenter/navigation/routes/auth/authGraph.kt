package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.auth

import androidx.compose.material3.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.baldomeronapoli.mlinvoice.presenter.ui.features.auth.AuthViewModel
import com.baldomeronapoli.mlinvoice.presenter.ui.features.auth.HomeHandleCommands
import com.baldomeronapoli.mlinvoice.presenter.ui.features.auth.screens.SignInScreen
import com.baldomeronapoli.mlinvoice.presenter.ui.features.auth.screens.SignUpScreen
import com.baldomeronapoli.mlinvoice.presenter.utils.composable
import com.baldomeronapoli.mlinvoice.presenter.utils.sharedViewModel

fun NavGraphBuilder.authGraph(
    navController: NavHostController
) {

    navigation(
        startDestination = AuthRoute.SignIn.route,
        route = AuthRoute.route
    ) {
        composable(AuthRoute.SignIn) {
            val viewModel = it.sharedViewModel<AuthViewModel>(navController)

            HomeHandleCommands(
                navController = navController,
                viewModel = viewModel
            )
            SignInScreen(
                state = viewModel.viewState.collectAsStateWithLifecycle(),
                onIntent = viewModel::setIntent,
            )
        }
        composable(AuthRoute.SignUp) {
            val viewModel = it.sharedViewModel<AuthViewModel>(navController)

            HomeHandleCommands(
                navController = navController,
                viewModel = viewModel
            )
            SignUpScreen(
                state = viewModel.viewState.collectAsStateWithLifecycle(),
                onIntent = viewModel::setIntent,
            )
        }
    }
}

