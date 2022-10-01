package com.emretnkl.odev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class LoginActivity : AppCompatActivity() {

    private lateinit var ivChangePasswordVisibility: ImageView // Image view has been created which provides password visibility.
    private var isVisibilityOn = false // Password visibility initialized as false.
    private lateinit var signUpButton : Button // Sign up button has been defined.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // View elements has been matched.
        ivChangePasswordVisibility  = findViewById(R.id.ivShowPassword)
        signUpButton = findViewById(R.id.btnSignUp)

        // Password visibility operation is occuring.
        ivChangePasswordVisibility.setOnClickListener {
            if (isVisibilityOn) {
                ivChangePasswordVisibility.setImageResource(R.drawable.ic_baseline_visibility_24)
                isVisibilityOn = false
            } else {
                ivChangePasswordVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                isVisibilityOn = true
            }
        }

        // Transition to Signup Activity without passing an argument.
        signUpButton.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }



    }
}