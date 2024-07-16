package com.example.notemanagerappzen8labs

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notemanagerappzen8labs.ui.screen.Login.LoginScreen


sealed class Screen(val route: String){
    object Login : Screen("login")
    object Home : Screen("home")
    object Detail : Screen("detail")
    object AddOrEdit : Screen("addOrEdit")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()
    
    NavHost(navController = navController, startDestination = Screen.Login.route){
        composable(Screen.Login.route){
            LoginScreen(navController, viewModel = hiltViewModel(), mainViewModel)
        }
    }
}