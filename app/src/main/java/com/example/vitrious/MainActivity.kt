package com.example.vitrious

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vitrious.pages.HomeScreen
import com.example.vitrious.pages.LoginPage
import com.example.vitrious.pages.SignUpPage
import com.example.vitrious.ui.theme.VitriousTheme
import com.example.vitrious.util.Screen
import com.example.vitrious.util.Status
import com.example.vitrious.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel: AuthViewModel by viewModels() // Corrected syntax
        setContent {
            VitriousTheme {
                MyAppNavigation(authViewModel)
            }
        }
    }
}

@Composable
fun MyAppNavigation(authViewModel: AuthViewModel) {
    val navController: NavHostController = rememberNavController()
    val authState = authViewModel.authState.observeAsState()

    NavHost(navController = navController, startDestination = Screen.splash.name) {

        composable(Screen.splash.name) {
            LaunchedEffect(
                authState.value
            ) {
                if (authState.value == Status.Authenticated) {
                    delay(1000)
                    navController.navigate(Screen.home.name) {
                        popUpTo(Screen.splash.name) {
                            inclusive = true
                        }
                    }
                } else {
                    delay(1000)
                    navController.navigate(Screen.login.name) {
                        popUpTo(Screen.splash.name) {
                            inclusive = true
                        }
                    }
                }
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

        }

        composable(Screen.login.name) {
            LoginPage(navController = navController, authViewModel = authViewModel)
        }
        composable(Screen.register.name) {
            SignUpPage(navController = navController, authViewModel = authViewModel)
        }

        composable(Screen.home.name) {
            LaunchedEffect(
                authState.value
            ) {
                if (authState.value == Status.NotAuthenticated) {
                    navController.navigate(Screen.login.name) {
                        popUpTo(Screen.splash.name) {
                            inclusive = true
                        }
                    }
                }
            }
            HomeScreen(authViewmodel = authViewModel, navController = navController)
        }

    }
}
