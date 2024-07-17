package com.example.notemanagerappzen8labs

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notemanagerappzen8labs.ui.screen.Detail.DetailScreen
import com.example.notemanagerappzen8labs.ui.screen.Home.HomeScreen
import com.example.notemanagerappzen8labs.ui.screen.Home.HomeViewModel
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
    val mainState = mainViewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(mainState.value.error){
        if(mainState.value.error != null && mainState.value.error != ""){
            Toast.makeText(context, mainState.value.error, Toast.LENGTH_LONG).show()
            mainViewModel.setError("")
        }
    }

    NavHost(navController = navController, startDestination = Screen.Login.route){
        composable(Screen.Login.route){
            LoginScreen(navController, viewModel = hiltViewModel(), mainViewModel)
        }
        composable(Screen.Home.route){
            HomeScreen(navController, viewModel = hiltViewModel<HomeViewModel>(), mainViewModel)
        }
        composable(Screen.Detail.route+ "?noteIndex={noteIndex}",
            arguments = listOf(
                navArgument("noteIndex"){
                    type = NavType.IntType
                    defaultValue = 1
                }
            )
            ) {
            it.arguments?.getInt("noteIndex").let { index ->
                var parentEntry = remember(it){
                    navController.getBackStackEntry(Screen.Home.route)
                }
                val homeViewModel: HomeViewModel = hiltViewModel(parentEntry)
                if(index != null){
                    DetailScreen(navController, homeViewModel, mainViewModel, index)
                }
            }
        }

    }
}