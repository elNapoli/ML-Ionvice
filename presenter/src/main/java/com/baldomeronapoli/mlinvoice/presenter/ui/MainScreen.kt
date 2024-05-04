package com.baldomeronapoli.mlinvoice.presenter.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.baldomeronapoli.mlinvoice.presenter.AppState
import com.baldomeronapoli.mlinvoice.presenter.base.composables.BaseScaffold
import com.baldomeronapoli.mlinvoice.presenter.navigation.AppNavigationHost
import com.baldomeronapoli.mlinvoice.presenter.rememberAppState
import com.baldomeronapoli.mlinvoice.presenter.ui.theme.MLInvoiceTheme

@Composable
fun MainScreen(appState: AppState = rememberAppState()) {
    MLInvoiceTheme {
        BaseScaffold(
            topBar = {

            },
            bottomBar = {


            }
        ) { innerPadding ->
            AppNavigationHost(
                modifier = Modifier.padding(innerPadding),
                navController = appState.navController,
            )
        }
    }

}