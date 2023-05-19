package com.example.projetcubemobile.models

import java.util.Date

class MessageForumModel {


    var id: Int = 0;
    var idUtilisateur: Int = 0;
    var idSubjectForum: Int = 0;

    lateinit var content: String
    lateinit var nomUtilisateur: String
    lateinit var dateCreation: Date

}