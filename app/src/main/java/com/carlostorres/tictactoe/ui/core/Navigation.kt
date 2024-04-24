package com.carlostorres.tictactoe.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                navigateToGame = {
                    navController.navigate(Routes.Game.route)
                }
            )
        }

        composable(Routes.Game.route){
            GameScreen()
        }

    }

}

sealed class Routes(val route: String){

    object Home : Routes("home")
    object Game : Routes("game")

}