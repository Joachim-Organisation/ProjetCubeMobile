package com.example.projetcubemobile.RecyclerViewAdapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.projetcubemobile.R

import com.example.projetcubemobile.RecyclerViewAdapter.placeholder.PlaceholderContent.PlaceholderItem
import com.example.projetcubemobile.Singleton
import com.example.projetcubemobile.databinding.FragmentSubjectForumListLayoutOnlyItemBinding
import com.example.projetcubemobile.models.CategorieModel
import com.example.projetcubemobile.models.SubjectForumModel
import com.example.projetcubemobile.tools.FragmentsTools
import com.example.projetcubemobile.ui.Fragments.SubjectForumPage
import java.util.LinkedList

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MySubjectForumRecyclerViewAdapter(
    private val values: LinkedList<SubjectForumModel>,
) : RecyclerView.Adapter<MySubjectForumRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentSubjectForumListLayoutOnlyItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentSubjectForumListLayoutOnlyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val titleTextView: TextView = binding.fragmentSubjectForumListLayoutOnlyItemTitle

        private val buttonAcceder: Button = binding.fragmentSubjectForumListLayoutOnlyItemButtonAcceder

        private lateinit var fragmentTools: FragmentsTools

        fun bind(item: SubjectForumModel) {
            titleTextView.text = item.title
            fragmentTools = FragmentsTools(Singleton.mainActivity)

            buttonAcceder.setOnClickListener {
                Singleton.idForumSubjectLoad = item.id
                fragmentTools.loadFragment(SubjectForumPage())
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + "'"
        }
    }

}