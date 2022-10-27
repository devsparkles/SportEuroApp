package com.devsparle.sporteuroapp.utils


sealed class Screen(val name: String, val route: String) {

    object Home : Screen(
        name = "Home", route = "home"
    )

    object Detail : Screen(
        name = "Detail",
        route = "detail"
    )
    
    object FullVideo : Screen(
        name = "FullVideo",
        route = "fullvideo"
    )
    
}