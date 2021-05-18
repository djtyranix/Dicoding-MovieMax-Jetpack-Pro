package com.nixstudio.moviemax.views.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.nixstudio.moviemax.R

class SettingsFragment : PreferenceFragmentCompat() {

    private var languageChange: Preference? = null
    private var darkModeSwitch: SwitchPreference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val preference = activity?.getSharedPreferences("moviemax-prefs", Context.MODE_PRIVATE)
        val editor = preference?.edit()

        languageChange = findPreference("select_language")

        val languageIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        languageChange?.intent = languageIntent

        darkModeSwitch = findPreference("enable_dark_mode")
        val isDarkModeEnabled = preference?.getBoolean("isDarkModeEnabled", false)

        if (isDarkModeEnabled == true) {
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

        darkModeSwitch?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, _ ->
                if (darkModeSwitch?.isChecked == true) {
                    AppCompatDelegate
                        .setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_NO
                        )

                    editor?.putBoolean("isDarkModeEnabled", false)
                    editor?.apply()
                    darkModeSwitch?.isChecked = false
                    Toast.makeText(
                        activity,
                        resources.getString(
                            R.string.dark_mode_isenabled,
                            resources.getString(R.string.disabled)
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    AppCompatDelegate
                        .setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_YES
                        )

                    editor?.putBoolean("isDarkModeEnabled", true)
                    editor?.apply()
                    darkModeSwitch?.isChecked = true
                    Toast.makeText(
                        activity,
                        resources.getString(
                            R.string.dark_mode_isenabled,
                            resources.getString(R.string.enabled)
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                false
            }
    }
}