package com.example.projetcubemobile.RecyclerViewAdapter

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetcubemobile.R
import com.example.projetcubemobile.databinding.CategorieScroolItem2Binding
import com.example.projetcubemobile.models.CategorieModel
import java.util.*

/**
 * [RecyclerView.Adapter] that can display a [CategorieModel].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCategorieRecyclerViewAdapter(
    private val values: LinkedList<CategorieModel>
) : RecyclerView.Adapter<MyCategorieRecyclerViewAdapter.ViewHolder>(), SpinnerAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategorieScroolItem2Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: CategorieScroolItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        private val titleTextView: TextView = binding.categorieScroolItem2TextView

        fun bind(item: CategorieModel) {
            titleTextView.text = item.nom
        }
    }

    override fun registerDataSetObserver(observer: DataSetObserver) {
        // TODO: Implement this method
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver) {
        // TODO: Implement this method
    }

    override fun getCount(): Int {
        return itemCount
    }

    override fun getItem(position: Int): Any {
        return values[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder: ViewHolder
        if (view == null) {
            val inflater = LayoutInflater.from(parent?.context)
            val binding = CategorieScroolItem2Binding.inflate(inflater, parent, false)
            holder = ViewHolder(binding)
            view = binding.root
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        holder.bind(values[position])
        return view
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun isEmpty(): Boolean {
        return itemCount == 0
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getView(position, convertView, parent)
    }
}
