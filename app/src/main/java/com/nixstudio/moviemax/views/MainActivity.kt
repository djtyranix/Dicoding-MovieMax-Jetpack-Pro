package com.nixstudio.moviemax.views

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.nixstudio.moviemax.R

class MainActivity : AppCompatActivity() {

    private var doubleBackToExitOnce: Boolean = false
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MovieMax)
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            val preference =
                this@MainActivity.getSharedPreferences("moviemax-prefs", Context.MODE_PRIVATE)
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

            setContentView(R.layout.activity_main)

            navHostFragment =
                supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        }
    }

    override fun onBackPressed() {
        if (navHostFragment.childFragmentManager.backStackEntryCount == 0) {
            if (doubleBackToExitOnce) {
                super.onBackPressed()
                finish()
                return
            }

            this.doubleBackToExitOnce = true

            Toast.makeText(
                this,
                resources.getString(R.string.exit_confirmation),
                Toast.LENGTH_SHORT
            ).show()

            Handler(Looper.getMainLooper()).postDelayed({
                kotlin.run { doubleBackToExitOnce = false }
            }, 2000)
        } else {
            super.onBackPressed()
            return
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
}