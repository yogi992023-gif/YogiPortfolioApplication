package com.yogi.driverapp.Screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun DashboardScreen(navController: NavController){

    Text("Dashboard", fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}