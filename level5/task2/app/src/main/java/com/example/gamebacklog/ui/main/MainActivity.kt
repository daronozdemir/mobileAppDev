package com.example.gamebacklog.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.ui.add.AddActivity
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener { view ->
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
        rvGames.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter

        createItemTouchHelper().attachToRecyclerView(rvGames)
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)


        mainActivityViewModel.games.observe(this, Observer { games ->
            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(games)

            gameAdapter.notifyDataSetChanged()
        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete_game_history -> {
                val deletedGames = ArrayList<Game>(games)
                mainActivityViewModel.deleteAllGame()
                showDeleteSnackbar(deletedGames)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]
                mainActivityViewModel.deleteGame(gameToDelete)
                this@MainActivity.showDeleteSnackbar(arrayListOf(gameToDelete))
            }
        }
        return ItemTouchHelper(callback)
    }

    fun showDeleteSnackbar(games: ArrayList<Game>) {
        val snackbar = Snackbar.make(findViewById(R.id.rvGames), "Succesfully deleted game", Snackbar.LENGTH_SHORT)
        snackbar.setAction(R.string.undo, UndoDeleteListener(games, mainActivityViewModel))
        snackbar.show()
    }

    class UndoDeleteListener(
        private val deletedGames: ArrayList<Game>,
        private val mainActivityViewModel: MainActivityViewModel
    ) : View.OnClickListener {
        override fun onClick(v: View) {
            deletedGames.forEach {
                mainActivityViewModel.insertGame(it)
            }
        }
    }
}
