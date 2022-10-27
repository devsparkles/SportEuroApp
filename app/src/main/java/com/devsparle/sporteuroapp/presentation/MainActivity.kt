package com.devsparle.sporteuroapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.devsparle.sporteuroapp.presentation.navigation.ScreenNavHost
import com.devsparle.sporteuroapp.ui.theme.SportEuroAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportEuroAppTheme {
                SportEuroApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SportEuroApp() {
    SportEuroAppTheme {
        val navController = rememberNavController()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            ScreenNavHost(
                navController = navController
            )
        }
    }
}




