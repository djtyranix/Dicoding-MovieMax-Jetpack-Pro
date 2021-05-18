package com.nixstudio.moviemax.views.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import com.nixstudio.moviemax.R

class HomeActivity : AppCompatActivity() {

    var doubleBackToExitOnce: Boolean = false
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
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