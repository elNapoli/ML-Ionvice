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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
) = remember(navController) {
    AppState(
        navController = navController,
        drawerState = drawerState,
        coroutineScope = coroutineScope,
        cameraPermissionState = cameraPermissionState
    )
}

@OptIn(ExperimentalPermissionsApi::class)
class AppState(
    val navController: NavHostController,
    var drawerState: DrawerState,
    var coroutineScope: CoroutineScope,
    val cameraPermissionState: PermissionState
) {
    var user = mutableStateOf<FirebaseUser?>(null)
    val itemsDrawer = listOf(HomeRoute.Index)
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
