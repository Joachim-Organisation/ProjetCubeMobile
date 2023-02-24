package com.example.projetcubemobile.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
 * Use the [CreateSubjectForumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateSubjectForumFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var fragmentTools: FragmentsTools
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