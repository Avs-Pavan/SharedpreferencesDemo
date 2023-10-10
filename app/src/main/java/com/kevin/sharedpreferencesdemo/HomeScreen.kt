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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // This is the logout button
        binding.logout.setOnClickListener {

            // Set the is_logged_in value to false
            editor.clear().apply()

            // Redirect the user to the LoginScreen
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}