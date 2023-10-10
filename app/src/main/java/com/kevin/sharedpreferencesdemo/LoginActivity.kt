package com.kevin.sharedpreferencesdemo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.sharedpreferencesdemo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

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

        initViews()
    }

    private fun initViews() {
        binding.login.setOnClickListener {
            val username = binding.name.text.toString()
            val password = binding.password.text.toString()

            if (username.isNotBlank() && password.isNotBlank()) {
                editor.putBoolean("is_logged_in", true)
                editor.apply()
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}