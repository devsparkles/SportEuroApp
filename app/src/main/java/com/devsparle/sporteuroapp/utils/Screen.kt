package com.devsparle.sporteuroapp.utils

import androidx.navigation.NavType
import androidx.navigation.navArgument


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

    ){
        const val videoUrlArg = "video_url"
        val routeWithArgs = "$route/{$videoUrlArg}"
        val arguments = listOf(
            navArgument(videoUrlArg) { type = NavType.StringType }
        )
    }

}