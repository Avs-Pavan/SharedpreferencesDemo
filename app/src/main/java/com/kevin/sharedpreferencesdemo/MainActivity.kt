package com.kevin.sharedpreferencesdemo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.sharedpreferencesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // This is the binding for the activity_main.xml layout file
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // This is the SharedPreferences object that we will use to store the user's login status
    private val preferences: SharedPreferences by lazy {
        getSharedPreferences(
            "my_preferences",
            MODE_PRIVATE
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // This is a delay of 2 seconds before the user is redirected to the login screen
        binding.root.postDelayed({

            // If the user is logged in, redirect them to the HomeScreen
            if (preferences.getBoolean("is_logged_in", false)) {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }
            // If the user is not logged in, redirect them to the LoginScreen
            else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)


    }

}