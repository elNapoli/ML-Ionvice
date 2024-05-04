package com.baldomeronapoli.mlinvoice.presenter.navigation.routes

import com.baldomeronapoli.mlinvoice.presenter.navigation.NavArg

open class Route(
    open val route: String,
    open val args: List<NavArg>? = listOf()
)