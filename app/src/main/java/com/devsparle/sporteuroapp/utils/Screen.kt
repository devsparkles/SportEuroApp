package com.devsparle.sporteuroapp.utils

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.devsparle.sporteuroapp.utils.Screen.FullVideo.videoUrlArg


sealed class Screen(val name: String, val route: String) {

    object Home : Screen(
        name = "Home", route = "home"
    )

    object Detail : Screen(
        name = "Detail",
        route = "detail"
    ) {
        const val backStackEntryStory = "story"
    }

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