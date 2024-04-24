package com.carlostorres.tictactoe.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carlostorres.tictactoe.ui.game.GameScreen
import com.carlostorres.tictactoe.ui.home.HomeScreen

@Composable
fun ContentWrapper(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ){

        composable(Routes.Home.route){
            HomeScreen(
                navigateToGame = { gameId, userId, owner ->
                    navController.navigate(
                        Routes.Game.createRoute(gameId, userId, owner)
                    )
                }
            )
        }

        composable(
            Routes.Game.route,
            arguments = listOf(
                navArgument("gameId"){type = NavType.StringType},
                navArgument("userId"){type = NavType.StringType},
                navArgument("owner"){type = NavType.BoolType},
            )
            ){
            GameScreen(
                gameId = it.arguments?.getString("gameId").orEmpty(),
                userId = it.arguments?.getString("userId").orEmpty(),
                owner = it.arguments?.getBoolean("owner") ?: false
            )
        }

    }

}

sealed class Routes(val route: String){

    object Home : Routes("home")
    object Game : Routes("game/{gameId}/{userId}/{owner}"){
        fun createRoute(gameId : String, userId : String, owner : Boolean) : String {

            return "game/$gameId/$userId/$owner"

        }
    }

}