package com.nixstudio.moviemax.views.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nixstudio.moviemax.R
import com.nixstudio.moviemax.databinding.SettingsActivityBinding

class SettingsActivity : AppCompatActivity() {

    private var binding: SettingsActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }

        setSupportActionBar(binding!!.homeToolbar.toolbarHome)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}