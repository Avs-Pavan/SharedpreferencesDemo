package com.kevin.sharedpreferencesdemo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.security.crypto.EncryptedSharedPreferences
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

    // EncryptedSharedPreferences for storing data locally on the device
    private lateinit var secureSharedPreferences: SharedPreferences
    private lateinit var secureEditor: SharedPreferences.Editor

    // SharedPreferences.Editor for editing the SharedPreferences
    private val editor: SharedPreferences.Editor by lazy {
        preferences.edit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initSharedPreferences()

        initViews()
    }

    private fun initSharedPreferences() {
        secureSharedPreferences = SecureSharedPrefs.getSharedPreferences(this)
        secureEditor = secureSharedPreferences.edit()
    }

    // Function to initialize the views
    private fun initViews() {


        binding.login.setOnClickListener {

            // Get the username and password from the EditTexts
            val username = binding.name.text.toString()
            val password = binding.password.text.toString()

            // Check if the username and password are not blank
            if (username.isNotBlank() && password.isNotBlank()) {

                // Store the logged in state in the SharedPreferences
                editor.putBoolean("is_logged_in", true)

                // Store the username and password in the encrypted SharedPreferences
                secureEditor.putString("username", username)
                secureEditor.putString("password", password)

                // Apply the changes
                secureEditor.apply()

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