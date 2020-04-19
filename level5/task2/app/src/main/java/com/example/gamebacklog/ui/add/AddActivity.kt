package com.example.gamebacklog.ui.add

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.model.Game.Companion.months

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import java.lang.Exception
import java.util.*

class AddActivity : AppCompatActivity() {

    private lateinit var addActivityViewModel: AddActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add a new game"

        initViewModel()
        initViews()
    }

    private fun initViews() {
        fab.setOnClickListener { view ->
            val date = validateDate(
                etDay.text.toString(),
                etMonth.text.toString(),
                etYear.text.toString()
            )

            addActivityViewModel.game.value?.apply {
                title = etTitle.text.toString()
                platform = etPlatform.text.toString()
                releaseDate = date
            }
            addActivityViewModel.insertGame()

        }
    }


    private fun initViewModel() {
        addActivityViewModel = ViewModelProviders.of(this).get(AddActivityViewModel::class.java)

        addActivityViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        addActivityViewModel.success.observe(this, Observer { success ->
            if (success) finish()
        })
    }


    private fun validateDate(day: String, month: String, year: String): Date? {
        var monthNumber = month.toIntOrNull()
        if (monthNumber == null){
            monthNumber = months.getOrElse(etMonth.text.toString().toLowerCase()) {
                return null
            }
        }
        return try {
            val dayNumber = day.toInt()

            if (monthNumber <= 12 && monthNumber > 0 && dayNumber<= 31) {
                GregorianCalendar(
                    year.toInt(),
                    monthNumber - 1,
                    dayNumber
                ).time
            } else null
        } catch (e: Exception) {
            null
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
