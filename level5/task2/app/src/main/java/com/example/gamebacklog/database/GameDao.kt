package com.example.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gamebacklog.model.Game

@Dao
interface GameDao {

    @Insert
    suspend fun insertGame(game: Game)

    @Query("SELECT * FROM gameTable")
    fun getGames(): LiveData<List<Game>>

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()
}