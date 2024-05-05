package com.baldomeronapoli.mlinvoice.presenter.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.mlinvoice.presenter.AppState
import com.baldomeronapoli.mlinvoice.presenter.base.composables.BaseScaffold
import com.baldomeronapoli.mlinvoice.presenter.navigation.AppNavigationHost
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.Route
import com.baldomeronapoli.mlinvoice.presenter.rememberAppState
import com.baldomeronapoli.mlinvoice.presenter.ui.theme.MLInvoiceTheme

@Composable
fun MainScreen(appState: AppState = rememberAppState(), startDestination: Route) {
    MLInvoiceTheme {
        BaseScaffold(
            topBar = {

            },
            bottomBar = {


            }
        ) { innerPadding ->
            ModalNavigationDrawer(
                drawerState = rememberDrawerState(DrawerValue.Closed),
                drawerContent = {
                    ModalDrawerSheet {
                        Text("Drawer title", modifier = Modifier.padding(16.dp))
                        Divider()
                        NavigationDrawerItem(
                            label = { Text(text = "Drawer Item") },
                            selected = false,
                            onClick = { /*TODO*/ }
                        )
                        // ...other drawer items
                    }
                }
            ) {
                AppNavigationHost(
                    navController = appState.navController,
                    startDestination = startDestination
                )
            }

        }
    }

}