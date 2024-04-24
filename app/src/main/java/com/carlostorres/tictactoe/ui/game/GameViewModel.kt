package com.carlostorres.tictactoe.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.tictactoe.data.network.FirebaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val firebaseService: FirebaseService
) : ViewModel() {

    lateinit var userId: String

    fun joinToGame(gameId: String, userId: String, owner: Boolean) {

        this.userId = userId

        if (owner){

            joinGameLikeOwner(gameId)

        } else {

            joinGameLikeGuest(gameId)

        }

    }

    private fun joinGameLikeGuest(gameId: String) {



    }

    private fun joinGameLikeOwner(gameId: String) {

        viewModelScope.launch {

            firebaseService.joinToGame(gameId).collect{

                Log.i("Aris", it.toString())

            }

        }

    }

}