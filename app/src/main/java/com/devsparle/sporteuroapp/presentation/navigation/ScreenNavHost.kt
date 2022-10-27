package com.devsparle.sporteuroapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devsparle.sporteuroapp.presentation.components.FeedScreen
import com.devsparle.sporteuroapp.utils.Screen

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
                    navController.navigateSingleTopTo(Screen.Detail.route)
                },
                onVideoClicked = {
                    navController.navigateSingleTopTo(Screen.FullVideo.route)
                }
            )
        }
        // detail screen is there to display detail story
        composable(
            route = Screen.Detail.route
        ) {


        }
        // video screen is there to display video in full screen
        composable(
            route = Screen.FullVideo.route
        ) {

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


