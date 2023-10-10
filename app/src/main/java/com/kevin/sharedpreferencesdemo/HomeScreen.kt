package com.kevin.sharedpreferencesdemo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.sharedpreferencesdemo.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {

    // This is the binding for the activity_home_screen.xml layout file
    private val binding by lazy { ActivityHomeScreenBinding.inflate(layoutInflater) }

    // This is the SharedPreferences object that we will use to store the user's login status
    private val preferences: SharedPreferences by lazy {
        getSharedPreferences(
            "my_preferences",
            MODE_PRIVATE
        )
    }

    // This is the SharedPreferences.Editor object that we will use to edit the SharedPreferences
    private val editor: SharedPreferences.Editor by lazy {
        preferences.edit()
    }


    // This is the EncryptedSharedPreferences object that we will use to store the user's credentials
    private lateinit var secureSharedPreferences: SharedPreferences
    private lateinit var secureEditor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initSharedPreferences()

        initViews()

    }

    private fun initViews() {
        // This is the logout button
        binding.logout.setOnClickListener {

            // Set the is_logged_in value to false
            editor.clear().apply()

            // Set the username and password values to null
            secureEditor.clear().apply()

            // Redirect the user to the LoginScreen
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Get the username from the SharedPreferences and display it in the TextView
        val name = secureSharedPreferences.getString("username", null)

        // Set the text of the TextView to "Welcome $name"
        binding.name.text = "Welcome $name"

    }

    private fun initSharedPreferences() {
        secureSharedPreferences = SecureSharedPrefs.getSharedPreferences(this)
        secureEditor = secureSharedPreferences.edit()
    }


}