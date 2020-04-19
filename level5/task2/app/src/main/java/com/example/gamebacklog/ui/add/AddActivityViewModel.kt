package com.example.gamebacklog.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.gamebacklog.database.GameRepository
import com.example.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val game = MutableLiveData<Game?>()
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()

    init {
        game.value = Game()
    }

    fun insertGame() {
        if (isGameValid()) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(game.value!!)
                }
                success.value = true
            }
        }
    }

    private fun isGameValid(): Boolean {
        return when {
            game.value == null -> {
                error.value = "Please enter all the fields"
                false
            }
            game.value!!.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            game.value!!.platform.isBlank() -> {
                error.value = "Platform must not be empty"
                false
            }
            game.value!!.releaseDate === null -> {
                error.value = "You've entered an invalid date!"
                false
            }
            else -> true
        }
    }
}