package com.example.vitrious.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vitrious.util.Screen
import com.example.vitrious.util.Status
import com.example.vitrious.viewmodel.AuthViewModel

@Composable
fun SignUpPage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }// Declare the email state variable

    val context = LocalContext.current

    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is Status.Error -> Toast.makeText(
                context,
                (authState.value as Status.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }



    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Signup Page", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email, // Bind the email state variable to the text field
            onValueChange = { email = it }, // Update the email value when the user types
            label = {
                Text("Email")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = username, // Bind the email state variable to the text field
            onValueChange = { username = it }, // Update the email value when the user types
            label = {
                Text("Username")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = password, // Bind the email state variable to the text field
            onValueChange = { password = it }, // Update the email value when the user types
            label = {
                Text("Password")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = phone, // Bind the email state variable to the text field
            onValueChange = { phone = it }, // Update the email value when the user types
            label = {
                Text("Phone")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            authViewModel.register(
                email = email,
                password = password,
                userName = username,
                phone = phone
            ) {
                navController.navigate(Screen.home.name)
            }
        }) {
            Text(text = "Create Account")
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = {
            navController.navigate(Screen.login.name)
        }) {
            Text(text = "Already have an account? Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpPage() {
    // Create a mock NavController and AuthViewModel
    val navController = rememberNavController()
    val authViewModel = AuthViewModel() // Ensure you initialize it properly
    SignUpPage(navController = navController, authViewModel = authViewModel)
}

