package com.baldomeronapoli.mlinvoice.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home.HomeRoute
import com.baldomeronapoli.mlinvoice.presenter.utils.currentRoute


@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),

    ) = remember(navController) {
    AppState(
        navController = navController,
    )
}

class AppState(
    val navController: NavHostController,
) {
    private val bottomNavigationItems = listOf(
        HomeRoute.Index,

    )

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val currentRoute = currentRoute(navController)
            ?: return false

        return bottomNavigationItems.any { it.route == currentRoute }
    }

    fun backNavigation() {
        navController.navigateUp()
    }

}
