package com.example.projetcubemobile.models

import java.util.LinkedList

class UtilisateurModel {

    var id: Int = 0
    var idRole: Int = 0

    lateinit var prenom: String
    lateinit var nom: String
    lateinit var password: String

    lateinit var mail: String

    var isActiver: Boolean = false

    lateinit var subjectsForum: LinkedList<SubjectForumModel>

}