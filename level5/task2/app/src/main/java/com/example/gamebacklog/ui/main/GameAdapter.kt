package com.example.gamebacklog.ui.main

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.item_game.view.*
import java.text.SimpleDateFormat
import java.util.*
import androidx.appcompat.app.AppCompatActivity

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.tvTitle.text = game.title
            itemView.tvPlatform.text = game.platform
            itemView.tvRelease.text = itemView.context.applicationContext.getString(
                R.string.release_date, SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
                    .format(game.releaseDate!!)
            )
        }
    }
}