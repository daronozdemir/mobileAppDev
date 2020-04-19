package com.example.gamebacklog.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "gameTable")
data class Game(
    var title: String = "Undefined",
    var platform: String = "Undefined",
    var releaseDate: Date? = null,
    @PrimaryKey var id : Long? = null
) : Parcelable
{
    companion object {
        val months = mapOf(
            "january" to 1,
            "february" to 2,
            "march" to 3,
            "april" to 4,
            "may" to 5,
            "june" to 6,
            "july" to 7,
            "august" to 8,
            "september" to 9,
            "october" to 10,
            "november" to 11,
            "december" to 12
        )
    }
}