package com.nixstudio.moviemax.views.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.views.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.statusBarColor = ContextCompat.getColor(this, R.color.lightblue_500)

        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        val isDarkModeEnabled = preference.getBoolean("isDarkModeEnabled", false)

        if (isDarkModeEnabled) {
            AppCompatDelegate
                .setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )
        } else {
            AppCompatDelegate
                .setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, HomeActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 2000)
    }
}