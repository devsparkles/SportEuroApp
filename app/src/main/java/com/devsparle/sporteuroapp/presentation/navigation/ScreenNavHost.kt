package com.devsparle.sporteuroapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devsparle.sporteuroapp.domain.model.FeedItem
import com.devsparle.sporteuroapp.presentation.screens.feed_detail.components.Details
import com.devsparle.sporteuroapp.presentation.screens.feed_home.components.FeedScreen
import com.devsparle.sporteuroapp.presentation.screens.videofullscreen.VideoFullScreen
import com.devsparle.sporteuroapp.utils.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ScreenNavHost(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        // home screen is the first destination
        composable(
            route = Screen.Home.route
        ) {
            FeedScreen(
                onStoryClicked = { story ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = Screen.Detail.backStackEntryStory,
                        value = story
                    )
                    navController.navigateSingleTopTo(Screen.Detail.route)
                },
                onVideoClicked = { url ->
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigateToFullScreenVideo(encodedUrl)
                }
            )
        }
        // detail screen is there to display detail story
        composable(
            route = Screen.Detail.route
        ) {
            val story =
                navController.previousBackStackEntry?.savedStateHandle?.get<FeedItem.Story>(Screen.Detail.backStackEntryStory)
            story?.let {
                Details(story = story) {
                    navController.navigateUp()
                }
            }

        }
        // video screen is there to display video in full screen
        composable(
            route = Screen.FullVideo.routeWithArgs,
            arguments = Screen.FullVideo.arguments
        ) { navBackStackEntry ->
            val videoUrl =
                navBackStackEntry.arguments?.getString(Screen.FullVideo.videoUrlArg, "")
            if (videoUrl != null) {
                VideoFullScreen(url = videoUrl)
            }
        }

    }
}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

private fun NavHostController.navigateToFullScreenVideo(url: String) {
    this.navigateSingleTopTo("${Screen.FullVideo.route}/$url")
}
