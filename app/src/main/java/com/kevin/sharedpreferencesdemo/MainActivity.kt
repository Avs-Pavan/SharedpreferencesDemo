package com.kevin.sharedpreferencesdemo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.sharedpreferencesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val preferences: SharedPreferences by lazy {
        getSharedPreferences(
            "my_preferences",
            MODE_PRIVATE
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.root.postDelayed({
            if (preferences.getBoolean("is_logged_in", false)) {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)


    }

}