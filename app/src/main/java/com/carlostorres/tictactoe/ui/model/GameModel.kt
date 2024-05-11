package com.carlostorres.tictactoe.ui.model

import com.carlostorres.tictactoe.data.network.model.GameData
import com.carlostorres.tictactoe.data.network.model.PlayerData

data class GameModel(
    val board: List<PlayerType>,
    val player1: PlayerModel,
    val player2: PlayerModel?,
    val playerTurn: PlayerModel,
    val gameId: String,
    val isGameReady: Boolean = false
) {

    fun toData(): GameData {

        return GameData(
            board = board.map { it.id },
            gameId = gameId,
            player1 = player1.toData(),
            player2 = player2?.toData(),
            playerTurn = playerTurn.toData()
        )

    }

}

class PlayerModel(
    val userId: String,
    val playerType: PlayerType
)
{
    fun toData():PlayerData{

        return PlayerData(
            userId = userId,
            playerType = playerType.id
        )

    }

}
sealed class PlayerType(
    val id: Int,
    val symbol: String
) {

    object FirstPlayer : PlayerType(
        id = 2,
        symbol = "x"
    )

    object SecondPlayer : PlayerType(
        id = 3,
        symbol = "o"
    )

    object Empty : PlayerType(
        id = 0,
        symbol = ""
    )

    companion object {

        fun getPlayerById(id: Int?): PlayerType {

            return when (id) {
                FirstPlayer.id -> FirstPlayer
                SecondPlayer.id -> SecondPlayer
                else -> Empty
            }

        }

    }

}