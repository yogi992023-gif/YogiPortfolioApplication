package com.yogi.interviewproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yogi.interviewproject.presentation.Location.screen.TrackingScreen
import com.yogi.interviewproject.presentation.post.screen.CreatePostScreen
import com.yogi.interviewproject.Screen.DashboardScreen
import com.yogi.interviewproject.SplashScreen
import com.yogi.interviewproject.presentation.post.screen.PostScreen
import com.yogi.interviewproject.presentation.post.screen.ProductListScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = "Splash_Screen"   // 👈 First screen
    ) {
        composable("Splash_Screen") {
            SplashScreen(navController)
        }

        composable("Post_Screen") {
            PostScreen(navController)
        }

        composable("Dashboard_Screen") {
            DashboardScreen(navController)
        }
        composable("Post_Screen_Create") {
            CreatePostScreen(navController)
        }

        composable("ProductListScreen") {
            ProductListScreen(navController)
        }

        composable("LocationScreen") {
            TrackingScreen(navController)
        }
    }
}