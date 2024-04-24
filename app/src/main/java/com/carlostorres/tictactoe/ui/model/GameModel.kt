package com.carlostorres.tictactoe.ui.model

class GameModel(
    val board : List<PlayerType>,
    val player1 : PlayerModel,
    val player2 : PlayerModel?,
    val playerTurn :  PlayerModel,
    val gameId : String
)

class PlayerModel(
    val userId: String,
    val playerType: PlayerType
)

sealed class PlayerType(
    val id:Int,
    val symbol : String
){

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

    companion object{

        fun getPlayerById(id : Int?) : PlayerType{

            return when(id){
                FirstPlayer.id -> FirstPlayer
                SecondPlayer.id -> SecondPlayer
                else -> Empty
            }

        }

    }

}