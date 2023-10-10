package com.kevin.sharedpreferencesdemo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.sharedpreferencesdemo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // ViewBinding for ActivityLogin
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    // SharedPreferences for storing data locally on the device
    private val preferences: SharedPreferences by lazy {
        getSharedPreferences(
            "my_preferences",
            MODE_PRIVATE
        )
    }

    // SharedPreferences.Editor for editing the SharedPreferences
    private val editor: SharedPreferences.Editor by lazy {
        preferences.edit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        initViews()
    }

    // Function to initialize the views
    private fun initViews() {


        binding.login.setOnClickListener {

            // Get the username and password from the EditTexts
            val username = binding.name.text.toString()
            val password = binding.password.text.toString()

            // Check if the username and password are not blank
            if (username.isNotBlank() && password.isNotBlank()) {

                // Store the username and password in the SharedPreferences
                editor.putBoolean("is_logged_in", true)
                editor.putString("username", username)
                editor.putString("password", password)

                // Apply the changes
                editor.apply()

                // Start the HomeScreen Activity
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}