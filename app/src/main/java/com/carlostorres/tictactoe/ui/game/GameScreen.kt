package com.carlostorres.tictactoe.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.tictactoe.ui.model.GameModel

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = hiltViewModel(),
    gameId: String,
    userId: String,
    owner: Boolean
){

    LaunchedEffect(true){

        gameViewModel.joinToGame(gameId, userId, owner)

    }

    val game : GameModel? by gameViewModel.game.collectAsState()

    Board(game)

}

@Composable
fun Board(game : GameModel?){

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(text = "ID de partida ${game?.gameId.orEmpty()}")

        val status = if (game?.isGameReady == true){

            if (game.isMyTurn){
                "Tu turno"
            }else{
                "Turno del rival"
            }

        }else{
            "Esperando por el jugador 2"
        }

        Text(text = "$status")

        Row() {
            GameItem()
            GameItem()
            GameItem()
        }
        Row() {
            GameItem()
            GameItem()
            GameItem()
        }
        Row() {
            GameItem()
            GameItem()
            GameItem()
        }

    }

}

@Preview
@Composable
fun GameItem(){

    Box (
        modifier = Modifier
            .padding(12.dp)
            .size(64.dp)
            .border(BorderStroke(2.dp, color = Color.Black)),
        contentAlignment = Alignment.Center
    ){

        Text(text = "X")

    }

}