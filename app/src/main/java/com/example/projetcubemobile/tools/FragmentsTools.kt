package com.example.projetcubemobile.tools

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projetcubemobile.R

class FragmentsTools {

    private lateinit var activity: AppCompatActivity;

    constructor(activity: AppCompatActivity){
        this.activity = activity;
    }

    public fun loadFragment(fragment: Fragment) {

        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}