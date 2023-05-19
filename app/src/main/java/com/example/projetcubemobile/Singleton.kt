package com.example.projetcubemobile

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projetcubemobile.models.SubjectForumModel
import com.example.projetcubemobile.models.UtilisateurModel
import java.util.LinkedList

object Singleton {
    lateinit var mainActivity: MainActivity;

    lateinit var fragment: Fragment;

    var CurrentUser: UtilisateurModel? = null

    var idForumSubjectLoad: Int = 0

    lateinit var listSubjectForumLoad: LinkedList<SubjectForumModel>
}