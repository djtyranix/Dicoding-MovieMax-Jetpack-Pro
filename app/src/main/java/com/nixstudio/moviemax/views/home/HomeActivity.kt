package com.nixstudio.moviemax.views.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.viewmodels.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private var mHomeFragment = HomeFragment()
    var doubleBackToExitOnce: Boolean = false
    private lateinit var navHostFragment: NavHostFragment
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHomeFragment = getForegroundFragment() as HomeFragment
    }

    fun getForegroundFragment(): Fragment? {
        navHostFragment =
            supportFragmentManager.findFragmentByTag("container_fragment") as NavHostFragment
        return when (navHostFragment) {
            else -> navHostFragment.childFragmentManager.fragments.get(0)
        }
    }

    override fun onBackPressed() {
        if (navHostFragment.childFragmentManager.backStackEntryCount == 0) {
            if (doubleBackToExitOnce) {
                super.onBackPressed()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mHomeFragment.setNavigation(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}