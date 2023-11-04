package com.example.assn5.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.assn5.ui.screens.HomeScreen


@Composable
fun RootNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(Routes.Home.route) { HomeScreen(navController = navController) }
    }
}