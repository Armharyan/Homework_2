package com.example.homework2

import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.compose.*

data class City(
    val name: String,
    val description: String,
    val imageRes: Int
)

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the App!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("second_screen") }) {
            Text(text = "Go to Second Screen")
        }
    }
}

@Composable
fun SecondScreen(
    cities: List<City>,
    navController: NavHostController
) {
    LazyColumn {
        items(cities) { city ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = city.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = city.description)
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = city.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(shape = RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        item {
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Go Back to Welcome Screen")
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "welcome_screen") {
                composable("welcome_screen") {
                    WelcomeScreen(navController)
                }
                composable("second_screen") {
                    val cities = listOf(
                        City("Yerevan", "Capital of Armenia", R.drawable.yerevan),
                        City("Washington", "Capital of the United States", R.drawable.washington),
                        City("Madrid", "Capital of Spain", R.drawable.madrid)
                        // Add more cities
                    )
                    SecondScreen(cities, navController)
                }
            }
        }
    }
}