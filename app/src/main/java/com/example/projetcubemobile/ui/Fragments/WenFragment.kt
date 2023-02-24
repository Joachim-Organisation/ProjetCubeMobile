package com.example.projetcubemobile.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.projetcubemobile.R
import com.example.projetcubemobile.Singleton
import com.example.projetcubemobile.tools.FragmentsTools

/**
 * A simple [Fragment] subclass.
 * Use the [WenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var fragmentsTools: FragmentsTools;

    override fun onPause(){
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
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wen, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WenFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}