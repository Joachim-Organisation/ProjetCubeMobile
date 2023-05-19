package com.example.projetcubemobile.RecyclerViewAdapter

import SubjectForumApi
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.projetcubemobile.Singleton
import com.example.projetcubemobile.databinding.FragmentItemBinding
import com.example.projetcubemobile.models.CategorieModel
import com.example.projetcubemobile.models.SubjectForumModel
import com.example.projetcubemobile.tools.FragmentsTools
import com.example.projetcubemobile.ui.Fragments.SettingsFragment
import com.example.projetcubemobile.ui.Fragments.SubjectForumListFragment
import com.example.projetcubemobile.ui.Fragments.SubjectForumListFragment2

import com.example.projetcubemobile.ui.Fragments.placeholder.PlaceholderContent.PlaceholderItem
import kotlinx.coroutines.NonDisposableHandle.parent
import java.util.*

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyForumCategorieListRecyclerViewAdapter(
    private val values: LinkedList<CategorieModel>,
) : RecyclerView.Adapter<MyForumCategorieListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.contentView.text = item.nom

        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val contentView: TextView = binding.MyForumCategorieListRecyclerViewAdapterTitle
        val buttonAcceder: Button = binding.buttonMyForumCategorieListRecyclerViewAdpaterAcceder

        val settingsFragment = FragmentsTools(Singleton.mainActivity)

        fun bind(item: CategorieModel) {
            buttonAcceder.setOnClickListener {
                val api = SubjectForumApi()


                api.getSubjectForumsByCategoryId(item.id) { list ->
                    if (list != null) {
                        Singleton.listSubjectForumLoad = list
                    }
                    settingsFragment.loadFragment(SubjectForumListFragment2())
                }
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}