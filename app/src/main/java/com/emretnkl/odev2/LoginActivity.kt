package com.emretnkl.odev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class LoginActivity : AppCompatActivity() {

    private lateinit var ivChangePasswordVisibility: ImageView // Parola görünürlüğünü sağlayan imageview oluşturuldu.
    private var isVisibilityOn = false // Parola görünürlüğü false olarak initialize edildi.
    private lateinit var signUpButton : Button // Kayıt butonu tanımlandı.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // View elemanları eşleştirildi.
        ivChangePasswordVisibility  = findViewById(R.id.ivShowPassword)
        signUpButton = findViewById(R.id.btnSignUp)

        // Parola görünürlüğü değiştirme işlemi gerçekleşiyor.
        ivChangePasswordVisibility.setOnClickListener {
            if (isVisibilityOn) {
                ivChangePasswordVisibility.setImageResource(R.drawable.ic_baseline_visibility_24)
                isVisibilityOn = false
            } else {
                ivChangePasswordVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                isVisibilityOn = true
            }
        }

        // Kayıt aktivitesine argümansız geçiş yapılıyor.
        signUpButton.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }



    }
}