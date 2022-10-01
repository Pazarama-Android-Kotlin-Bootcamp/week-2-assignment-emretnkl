package com.emretnkl.odev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class SignupActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton // The transition button to Login Activity has been defined.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        backButton = findViewById(R.id.imgBttnBack)  // The button has been matched with view element.

        // Transition to Login Activity without passing an argument.
        backButton.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}