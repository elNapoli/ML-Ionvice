package com.baldomeronapoli.mlinvoice.presenter.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.Route

fun NavGraphBuilder.composable(
    navCommand: Route,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    navCommand.args?.let {
        composable(
            route = navCommand.route,
            arguments = it.map { arg ->
                navArgument(arg.key) { type = arg.navType }
            }
        ) { navBackStackEntry ->
            content(navBackStackEntry)
        }
    }
}