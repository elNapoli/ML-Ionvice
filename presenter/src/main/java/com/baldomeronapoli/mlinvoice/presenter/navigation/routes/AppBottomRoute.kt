package com.baldomeronapoli.mlinvoice.presenter.navigation.routes

import androidx.compose.ui.graphics.vector.ImageVector
import com.baldomeronapoli.mlinvoice.presenter.navigation.NavArg

open class AppBottomRoute(
    override val route: String,
    val label: String,
    val icon: ImageVector,
    override val args: List<NavArg>? = listOf()
) : Route(route = route)
