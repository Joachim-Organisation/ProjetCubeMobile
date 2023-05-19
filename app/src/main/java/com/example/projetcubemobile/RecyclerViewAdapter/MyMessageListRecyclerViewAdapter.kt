package com.example.projetcubemobile.RecyclerViewAdapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.projetcubemobile.RecyclerViewAdapter.placeholder.PlaceholderContent.PlaceholderItem
import com.example.projetcubemobile.databinding.FragmentSubjectForumListLayoutOnlyItemBinding
import com.example.projetcubemobile.databinding.MessageListFragmentItem2Binding
import com.example.projetcubemobile.models.MessageForumModel
import com.example.projetcubemobile.models.SubjectForumModel

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMessageListRecyclerViewAdapter(
    values: List<MessageForumModel>
) : RecyclerView.Adapter<MyMessageListRecyclerViewAdapter.ViewHolder>() {
    private val sortedValues = values.sortedWith(compareBy<MessageForumModel> { it.dateCreation }).reversed()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MessageListFragmentItem2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = sortedValues[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = sortedValues.size

    inner class ViewHolder(binding: MessageListFragmentItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber

        private val contentTextView: TextView = binding.idMessageListFragmentContent
        private val utilisateurTextView: TextView = binding.idMessageListFragmentUtilisateur

        fun bind(item: MessageForumModel) {
            contentTextView.text = item.content
            utilisateurTextView.text = item.nomUtilisateur
        }
    }
}
