package com.example.projetcubemobile.ui.Fragments

import UtilisateurApi
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.activity.addCallback
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.projetcubemobile.R
import com.example.projetcubemobile.Singleton
import com.example.projetcubemobile.models.UtilisateurModel
import com.example.projetcubemobile.tools.FragmentsTools
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var buttonCreateResource: Button;
    private lateinit var buttonForum: Button;

    private lateinit var settingsFragments: FragmentsTools;

    private lateinit var mailConnexion: TextInputEditText;
    private lateinit var password: TextInputEditText;
    private lateinit var buttonConnexion: Button;

    private lateinit var buttonClosePopopConnexion: Button

    override fun onPause(){
        super.onPause()
        Singleton.fragment = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        if(Singleton.CurrentUser == null){
            return inflater.inflate(R.layout.fragment_connexion, container, false)
        }

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.settingsFragments = FragmentsTools(Singleton.mainActivity)

        if(Singleton.CurrentUser == null) { //Si la personne n'est pas connecté
           this.mailConnexion = view.findViewById(R.id.fragment_connexion_mail)

            this.password = view.findViewById(R.id.fragment_connexion_password)

            this.buttonConnexion = view.findViewById(R.id.input_fragment_connexion_connection_button)
            var currentUser: UtilisateurModel
            this.buttonConnexion.setOnClickListener {
                val api = UtilisateurApi()

                api.getUtilisateurByEmailAndPassword(this.mailConnexion.text.toString(), this.password.text.toString()) { currentUser ->
                    if (currentUser != null) {
                        Singleton.CurrentUser = currentUser
                        this.settingsFragments.loadFragment(HomeFragment())

                    } else {
                        // Afficher un message d'erreur si l'utilisateur n'a pas été trouvé


                        this.showPopup(view);
                    }
                }
            }
        }else{ //Si la personne est bien connecté


            val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
                settingsFragments.loadFragment(Singleton.fragment);
            }

            this.buttonCreateResource = view.findViewById(R.id.button_home_creer_ressource)
            this.buttonCreateResource.setOnClickListener {
                this.settingsFragments.loadFragment(CreateRessources())
            }

            this.buttonForum = view.findViewById(R.id.button_home_forum)
            this.buttonForum.setOnClickListener {
                this.settingsFragments.loadFragment(MainPageForumFragment())
            }
        }
    }


    private fun showPopup(view: View) {
        activity?.runOnUiThread {
            val inflater =
                Singleton.mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.popup_layout_connexion_failed, null)

            val popupWindow = PopupWindow(
                popupView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true
            )

            this.buttonClosePopopConnexion = popupView.findViewById(R.id.popup_layout_connexion_failed_button_close)

            this.buttonClosePopopConnexion.setOnClickListener {
                this.closePopup(popupView)
            }

            // Afficher le pop-up centré sur l'écran
            popupWindow.showAtLocation(
                view.findViewById(R.id.fragment_home_main_layout), // ID de la vue parent
                Gravity.CENTER,
                0,
                0
            )
        }
    }
    fun closePopup(view: View) {
        // Fermer le pop-up
            val popupWindow = PopupWindow(view.context)
            popupWindow.dismiss()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}