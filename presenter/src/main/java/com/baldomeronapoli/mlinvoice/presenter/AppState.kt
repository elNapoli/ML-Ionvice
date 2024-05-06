package com.baldomeronapoli.mlinvoice.presenter

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.home.HomeRoute
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.storage.StorageRoute
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(navController) {
    AppState(
        navController = navController,
        drawerState = drawerState,
        coroutineScope = coroutineScope,
    )
}

class AppState(
    val navController: NavHostController,
    var drawerState: DrawerState,
    var coroutineScope: CoroutineScope,
) {
    var user = mutableStateOf<FirebaseUser?>(null)
    val itemsDrawer = listOf(HomeRoute.Index, StorageRoute.Index)
    private fun openDrawer() {
        coroutineScope.launch {
            drawerState.open()
        }
    }

    private fun closeDrawer() {
        coroutineScope.launch {
            drawerState.close()
        }
    }

    fun toggleDrawer() {
        coroutineScope.launch {
            if (drawerState.isOpen) {
                closeDrawer()
            } else {
                openDrawer()
            }
        }
    }

}
