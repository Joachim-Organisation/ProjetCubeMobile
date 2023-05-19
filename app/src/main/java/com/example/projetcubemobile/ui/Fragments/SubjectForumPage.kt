package com.example.projetcubemobile.ui.Fragments

import SubjectForumApi
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetcubemobile.R
import com.example.projetcubemobile.RecyclerViewAdapter.MyMessageListRecyclerViewAdapter
import com.example.projetcubemobile.Singleton
import com.example.projetcubemobile.models.MessageForumModel
import com.example.projetcubemobile.models.SubjectForumModel
import com.example.projetcubemobile.tools.API.MessageForumApi
import com.example.projetcubemobile.tools.FragmentsTools
import com.example.projetcubemobile.tools.KeyboardListener
import com.google.android.material.textfield.TextInputEditText


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SubjectForumPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubjectForumPage : Fragment() {

    private lateinit var ennonceDemande: TextView

    private lateinit var commentaire: TextInputEditText

    private lateinit var buttonEnvoyer: Button
    private lateinit var buttonRefresh: Button

    private lateinit var fragmentTool: FragmentsTools

    private lateinit var listMessage: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        return inflater.inflate(R.layout.fragment_subject_forum_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


            KeyboardListener(view, object : KeyboardListener.KeyboardVisibilityListener {
                override fun onKeyboardVisibleChanged(isVisible: Boolean) {
                    // Faites quelque chose lorsque le clavier est ouvert ou fermé
                    if (isVisible) {
                        // Le clavier est ouvert
                        Singleton.mainActivity.changAppBarVisibility()
                    } else {
                        // Le clavier est fermé
                        Singleton.mainActivity.changAppBarVisibility()
                    }
                }
            })

        // Inflate the layout for this fragment
        val api = SubjectForumApi()
        val messageForumApi = MessageForumApi()

        var subject = SubjectForumModel()

        this.ennonceDemande = view!!.findViewById(R.id.id_fragment_subjet_forum_page_subjet)
        this.commentaire = view!!.findViewById(R.id.id_fragment_subject_forum_page_commentaire)
        this.buttonEnvoyer = view!!.findViewById(R.id.id_fragment_subject_forum_page_envoyer)
        this.buttonRefresh = view!!.findViewById(R.id.id_fragment_subject_forum_button_refresh)
        this.fragmentTool = FragmentsTools(Singleton.mainActivity)
        this.listMessage = view!!.findViewById(R.id.id_fragment_subject_forum_page_list_message)

        // Forum
        val idSubjectForum =
            Singleton.idForumSubjectLoad // Remplacez 1 par l'id du sujet de forum correspondant
        messageForumApi.getMessageForums(idSubjectForum) { messageForumList ->
            activity?.runOnUiThread {
                val layoutManager = LinearLayoutManager(context) // Ajout du LinearLayoutManager
                listMessage.layoutManager =
                    layoutManager // Attribution du LayoutManager au RecyclerView

                listMessage.adapter = MyMessageListRecyclerViewAdapter(messageForumList)

            }
        }
        activity?.runOnUiThread {
            val subjectForumApi = SubjectForumApi()

            val subjectForumId =
                Singleton.idForumSubjectLoad // l'ID du sujet que vous voulez récupérer

            subjectForumApi.getSubjectForumById(subjectForumId) { subjectForum -> //Il faut bien faire les processus d'initialisation des texts view à l'intérieur à cause de l'asynchrone.
                if (subjectForum != null) {
                    // faire quelque chose avec le sujet récupéré
                    subject = subjectForum

                    ennonceDemande.text = subject.text;

                    this.buttonEnvoyer.setOnClickListener {
                        var messageEnvoyer = MessageForumModel()

                        messageEnvoyer.idUtilisateur = Singleton.CurrentUser!!.id
                        messageEnvoyer.idSubjectForum = Singleton.idForumSubjectLoad
                        messageEnvoyer.content = commentaire.text.toString()

                        messageForumApi.createMessageForum(messageEnvoyer)

                        fragmentTool.loadFragment(SubjectForumPage())
                    }

                    this.buttonRefresh.setOnClickListener {
                        fragmentTool.loadFragment(SubjectForumPage())
                    }

                    Log.d("SubjectForum", "SubjectForum title: ${subjectForum.title}")
                } else {
                    // gérer l'erreur
                    Log.e("SubjectForum", "Failed to get subject forum with ID: $subjectForumId")
                }
            }
        }
    }
}