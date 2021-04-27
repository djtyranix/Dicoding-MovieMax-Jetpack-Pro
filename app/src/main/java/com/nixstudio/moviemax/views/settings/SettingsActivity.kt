package com.nixstudio.moviemax.views.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        private var languageChange: Preference? = null

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            languageChange = findPreference("select_language")

            val languageIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            languageChange?.intent = languageIntent
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}