package com.baldomeronapoli.mlinvoice.presenter.base

import androidx.compose.runtime.Composable
import com.baldomeronapoli.mlinvoice.presenter.AppState
import com.baldomeronapoli.mlinvoice.presenter.base.composables.BaseScaffold
import com.baldomeronapoli.mlinvoice.presenter.rememberAppState
import com.baldomeronapoli.mlinvoice.presenter.ui.theme.MLInvoiceTheme

@Composable
fun BasePreview(
    content: @Composable () -> Unit = {},
) {
    val appState: AppState = rememberAppState()
    MLInvoiceTheme {
        BaseScaffold(
            topBar = {
            },
            bottomBar = {


            }
        ) { innerPadding ->
            content()
        }
    }

}