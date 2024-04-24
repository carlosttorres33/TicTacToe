package com.carlostorres.tictactoe.ui.home

import androidx.lifecycle.ViewModel
import com.carlostorres.tictactoe.data.network.FirebaseService
import com.carlostorres.tictactoe.data.network.model.GameData
import com.carlostorres.tictactoe.data.network.model.PlayerData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseService: FirebaseService
) : ViewModel() {

    fun onCreateGame() {

        firebaseService.createGame(createNewGame())

    }

    private fun createNewGame(): GameData{

        val currentPlayer = PlayerData(playerType = 1)

        return GameData(

            board = List(9){ 0 },
            player1 = currentPlayer,
            playerTurn = currentPlayer,
            player2 = null

        )

    }

    fun onJoinGame(id: String) {

    }

}