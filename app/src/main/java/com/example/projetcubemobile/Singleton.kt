package com.example.projetcubemobile

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projetcubemobile.models.UtilisateurModel

object Singleton {
    lateinit var mainActivity: AppCompatActivity;

    lateinit var fragment: Fragment;

    var CurrentUser: UtilisateurModel? = null
}