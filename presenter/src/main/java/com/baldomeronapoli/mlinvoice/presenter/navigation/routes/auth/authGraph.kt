package com.baldomeronapoli.mlinvoice.presenter.navigation.routes.auth

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home.HomeRoute
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
            val state = viewModel.viewState.collectAsStateWithLifecycle()
            HomeHandleCommands(
                navController = navController,
                viewModel = viewModel
            )
            // TODO: analizar donde debe ir esta logica
            if (state.value.userState.data !== null) {
                navController.navigate(HomeRoute.Index.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            } else {
                SignInScreen(
                    state = state,
                    onIntent = viewModel::setIntent,
                )
            }

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

