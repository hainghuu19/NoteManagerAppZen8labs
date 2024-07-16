package com.example.notemanagerappzen8labs.ui.screen.Login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.notemanagerappzen8labs.MainViewModel
import com.example.notemanagerappzen8labs.Screen
import com.example.notemanagerappzen8labs.common.enum.LoadStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel,
    mainViewModel: MainViewModel
) {
    val state = viewModel.uiState.collectAsState()

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        if(state.value.status is LoadStatus.Loading){
            CircularProgressIndicator()
        }else if (state.value.status is LoadStatus.Success){
            navController.navigate(Screen.Home.route)
        }else{
            if(state.value.status is LoadStatus.Error){
                mainViewModel.setError(state.value.status.description)
                viewModel.reset()
            }
            OutlinedTextField(value = state.value.username, onValueChange = {
                viewModel.updateUsername(it)
            }, modifier = Modifier.padding(16.dp),
                label = {Text("Username")}
            )
            OutlinedTextField(value = state.value.password, onValueChange = {
                viewModel.updatePassword(it)
            }, modifier = Modifier.padding(16.dp),
                label = {Text("Password")}
            )
            ElevatedButton(onClick = { viewModel.login() }, modifier = Modifier.padding(16.dp)){
                Text("Login")
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen(
        navController = NavHostController(LocalContext.current),
        viewModel = LoginViewModel(null, null),
        mainViewModel = MainViewModel()
    )
}