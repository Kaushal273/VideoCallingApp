package com.example.videocallingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.videocallingapp.ui.theme.VideoCallingAppTheme

const val APP_ID = "7643cd878f764265921b9fefebd69e25"

@ExperimentalUnsignedTypes
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VideoCallingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background,
                        modifier = Modifier.padding(16.dp)
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "room_screen"
                    ){
                        composable(route = "room_screen"){
                            RoomScreen(onNavigate = navController::navigate)
                        }
                        composable(
                            route = "video_screen/{roomName}",
                            arguments = listOf(
                                navArgument(name = "roomName"){
                                    type = NavType.StringType
                                }
                            )
                        ){
                            val roomName = it.arguments?.getString("roomName")?: return@composable
                            VideoScreen(roomName = roomName,
                            onNavigateUp = navController::navigateUp)
                        }
                    }
                }
            }
        }
    }
}


