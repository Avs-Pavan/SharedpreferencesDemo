package com.kevin.sharedpreferencesdemo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.sharedpreferencesdemo.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {

    private val binding by lazy { ActivityHomeScreenBinding.inflate(layoutInflater) }

    private val preferences: SharedPreferences by lazy {
        getSharedPreferences(
            "my_preferences",
            MODE_PRIVATE
        )
    }

    private val editor: SharedPreferences.Editor by lazy {
        preferences.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.logout.setOnClickListener {
            editor.clear().apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}