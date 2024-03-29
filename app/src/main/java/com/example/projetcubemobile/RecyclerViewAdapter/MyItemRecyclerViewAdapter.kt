package com.example.projetcubemobile.RecyclerViewAdapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.projetcubemobile.R

import com.example.projetcubemobile.RecyclerViewAdapter.placeholder.PlaceholderContent.PlaceholderItem
import com.example.projetcubemobile.databinding.FragmentListSubjectByCategoryBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentListSubjectByCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        //holder.idView.text = item.id
       // holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentListSubjectByCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
       // val idView: TextView = binding.itemNumber
        //val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString()
        }
    }

}