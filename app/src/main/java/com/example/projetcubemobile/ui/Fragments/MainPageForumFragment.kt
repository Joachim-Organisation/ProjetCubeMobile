package com.example.projetcubemobile.ui.Fragments

import SubjectForumApi
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import com.example.projetcubemobile.R
import com.example.projetcubemobile.Singleton
import com.example.projetcubemobile.tools.FragmentsTools

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainPageForumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainPageForumFragment : Fragment() {

    private lateinit var settingsFragment: FragmentsTools;

    private lateinit var buttonCreerSujet: Button;
    private lateinit var buttonListCategorie: Button;

    private lateinit var fragmentsTools: FragmentsTools

    private lateinit var buttonMesSujet: Button

    override fun onPause() {
        super.onPause()
        Singleton.fragment = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.fragmentsTools = FragmentsTools(Singleton.mainActivity)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            fragmentsTools.loadFragment(Singleton.fragment);
        }
        arguments?.let {


        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_page_forum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.settingsFragment = FragmentsTools(Singleton.mainActivity);

        this.buttonCreerSujet = view.findViewById(R.id.button_main_page_forum_creer_sujet)
        this.buttonCreerSujet.setOnClickListener {
            this.settingsFragment.loadFragment(CreateSubjectForumFragment())
        }

        this.buttonListCategorie = view.findViewById(R.id.button_main_page_forum_list_categories)
        this.buttonListCategorie.setOnClickListener {
            this.settingsFragment.loadFragment(ForumCategorieListFragment())
        }

        this.buttonMesSujet = view.findViewById(R.id.fragment_main_page_forum_acceder_a_mes_sujets)

        val api = SubjectForumApi()
        this.buttonMesSujet.setOnClickListener {
            activity?.runOnUiThread {
                api.getSubjectForumByUserCurrent { item ->
                    Singleton.CurrentUser!!.subjectsForum = item!!;
                    this.settingsFragment.loadFragment(SubjectForumListFragment())

                }
            }
        }

}

companion object {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainPageForumFragment.
     */
    // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
        MainPageForumFragment().apply {
            arguments = Bundle().apply {

            }
        }
}
}