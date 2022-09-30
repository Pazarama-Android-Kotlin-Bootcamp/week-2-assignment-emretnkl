package com.emretnkl.odev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class SignupActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton // Login Activity'e geçiş butonu tanımlandı.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        backButton = findViewById(R.id.imgBttnBack)  // Buton view elemanı ile eşleştirildi.

        // Login Activity'e argümansız geçiş yapılıyor.
        backButton.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}