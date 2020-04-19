package com.example.gamebacklog.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gamebacklog.database.GameRepository
import com.example.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val games = gameRepository.getGames()

    fun deleteGame(game: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteGame(game)
            }
        }
    }

    fun deleteAllGame() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAllGames()
            }
        }
    }

    fun insertGame(game: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }
    }
}
