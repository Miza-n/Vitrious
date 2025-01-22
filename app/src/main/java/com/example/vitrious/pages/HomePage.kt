package com.example.vitrious.pages

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.vitrious.designs.NewsDesign
import com.example.vitrious.util.devsList
import com.example.vitrious.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(authViewmodel: AuthViewModel, navController: NavHostController) {

    val list = devsList

    var search by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Back Button
        Button(
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(40.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFE5E4E4)),
            onClick = {}
        ) {
            Icon(
                tint = Color.Black,
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

        // Title Text
        Text(text = "Discover", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(text = "News from all around the world", color = Color.Gray, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(20.dp))

        // Search Bar
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            placeholder = {
                Text(text = "Search", fontSize = 15.sp, color = Color.Gray)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            },
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF799CD3),
                unfocusedBorderColor = Color(0xFFD3D3D3)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Category Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("All", "Politic", "Education", "Sport", "Game").forEach { category ->
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF799CD3)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(category, color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // News List
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            list.forEach {
                NewsDesign(it)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}
