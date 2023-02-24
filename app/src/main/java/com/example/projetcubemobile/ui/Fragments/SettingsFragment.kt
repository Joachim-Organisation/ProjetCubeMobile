package com.example.projetcubemobile.ui.Fragments

import android.os.Bundle
import androidx.activity.addCallback
import androidx.preference.PreferenceFragmentCompat
import com.example.projetcubemobile.R
import com.example.projetcubemobile.Singleton
import com.example.projetcubemobile.tools.FragmentsTools

class SettingsFragment : PreferenceFragmentCompat() {


    private lateinit var fragmentsTools: FragmentsTools

    override fun onPause(){
        super.onPause()
        Singleton.fragment = this
    }

    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        this.fragmentsTools = FragmentsTools(Singleton.mainActivity)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            fragmentsTools.loadFragment(Singleton.fragment);
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}