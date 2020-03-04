package com.example.studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_portal.*

const val EXTRA_PORTAL = "EXTRA_PORTAL"

class AddPortal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portal)

        btnAddPortal.setOnClickListener {
            onSaveClick()
        }
    }

    private fun onSaveClick() {
        if (txtTitle.text.isNullOrBlank() || txtUrl.text.isNullOrBlank() || txtUrl.text.toString() == "http://") {
            Toast.makeText(this,"The title or url cannot be empty!", Toast.LENGTH_SHORT).show()
        } else {
            var portal = Portal(txtTitle.text.toString(), txtUrl.text.toString())
            var resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL, portal)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
