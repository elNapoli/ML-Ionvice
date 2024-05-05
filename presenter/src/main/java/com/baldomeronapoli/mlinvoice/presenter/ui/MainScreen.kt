package com.baldomeronapoli.mlinvoice.presenter.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.mlinvoice.presenter.AppState
import com.baldomeronapoli.mlinvoice.presenter.R
import com.baldomeronapoli.mlinvoice.presenter.base.composables.BaseScaffold
import com.baldomeronapoli.mlinvoice.presenter.navigation.AppNavigationHost
import com.baldomeronapoli.mlinvoice.presenter.navigation.routes.Route
import com.baldomeronapoli.mlinvoice.presenter.ui.theme.MLInvoiceTheme
import com.baldomeronapoli.mlinvoice.presenter.utils.currentRoute
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(appState: AppState, startDestination: Route) {
    MLInvoiceTheme {
        ModalNavigationDrawer(
            drawerState = appState.drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text(appState.user.value?.email ?: "", modifier = Modifier.padding(16.dp))
                    Divider()
                    appState.itemsDrawer.forEach {
                        NavigationDrawerItem(
                            shape = MaterialTheme.shapes.extraSmall,
                            label = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = it.icon,
                                        contentDescription = null
                                    )
                                    Text(
                                        modifier = Modifier.padding(start = 16.dp),
                                        text = it.label.replaceFirstChar { text ->
                                            if (text.isLowerCase()) text.titlecase(
                                                Locale.getDefault()
                                            ) else it.toString()
                                        },

                                        )
                                }
                            },
                            selected = currentRoute(appState.navController) == it.route,
                            onClick = { /*TODO*/ }
                        )
                    }

                }
            }
        ) {
            BaseScaffold(
                topBar = {
                    if (appState.user.value !== null) {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(stringResource(id = R.string.title_top_app_bar))
                            },
                            navigationIcon = {
                                IconButton(onClick = { appState.toggleDrawer() }) {
                                    Icon(
                                        imageVector = Icons.Filled.Menu,
                                        contentDescription = null
                                    )
                                }

                            },
                        )
                    }

                },
                bottomBar = {


                }
            ) { innerPadding ->
                AppNavigationHost(
                    modifier = Modifier.padding(innerPadding),
                    startDestination = startDestination,
                    appState = appState
                )

            }
        }
    }

}