package com.example.projetcubemobile.ui.Fragments

import SubjectForumApi
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetcubemobile.R
import com.example.projetcubemobile.RecyclerViewAdapter.MyCategorieRecyclerViewAdapter
import com.example.projetcubemobile.RecyclerViewAdapter.MyForumCategorieListRecyclerViewAdapter
import com.example.projetcubemobile.RecyclerViewAdapter.MyItemRecyclerViewAdapter
import com.example.projetcubemobile.RecyclerViewAdapter.placeholder.PlaceholderContent
import com.example.projetcubemobile.Singleton
import com.example.projetcubemobile.models.CategorieModel
import com.example.projetcubemobile.models.SubjectForumModel
import com.example.projetcubemobile.tools.FragmentsTools
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateSubjectForumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateSubjectForumFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var fragmentTools: FragmentsTools

    private lateinit var textInputTitle: TextInputEditText

    private lateinit var textInputText: TextInputEditText

    private lateinit var buttonSubmit: Button

    private lateinit var spinnerCategories: Spinner

    private lateinit var settingsFragments: FragmentsTools;


    private var columnCount = 1

    override fun onPause(){
        super.onPause()
        Singleton.fragment = this
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.fragmentTools = FragmentsTools(Singleton.mainActivity)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            fragmentTools.loadFragment(Singleton.fragment);
        }

        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_subject_forum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.textInputTitle = view.findViewById(R.id.input_fragment_create_subject_forum_title)
        this.textInputText = view.findViewById(R.id.input_fragment_create_subject_forum_text)
        this.buttonSubmit = view.findViewById(R.id.button_fragment_create_subject_forum_valider)
        this.spinnerCategories = view.findViewById(R.id.fragment_create_subjet_forum_spinner)

        this.settingsFragments = FragmentsTools(Singleton.mainActivity)

        val api = SubjectForumApi()
        api.getAllCategories { listCategories ->
            activity?.runOnUiThread {
                this.spinnerCategories.adapter = MyCategorieRecyclerViewAdapter(listCategories)
            }
        }

        this.buttonSubmit.setOnClickListener {
            var subjectForumModel = SubjectForumModel();

            subjectForumModel.text = this.textInputText.text.toString()
            subjectForumModel.title = this.textInputTitle.text.toString()

            val selectedCategory = this.spinnerCategories.selectedItem as CategorieModel
            subjectForumModel.idCategorie = selectedCategory.id

            subjectForumModel.idUtilisateur = Singleton.CurrentUser!!.id

            activity?.runOnUiThread {
                api.createSubjectForum(subjectForumModel)
                settingsFragments.loadFragment(MainPageForumFragment())
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
         * @return A new instance of fragment CreateSubjectForumFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateSubjectForumFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}