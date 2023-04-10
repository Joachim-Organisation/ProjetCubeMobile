package com.example.projetcubemobile.models

import java.util.LinkedList

class SubjectForumModel {

    var id: Int = 0;
    var idCategorie: Int = 0;
    var idUtilisateur: Int = 0;


    lateinit var title: String;
    lateinit var text: String;

    lateinit var categorieModel:  LinkedList<CategorieModel>

}