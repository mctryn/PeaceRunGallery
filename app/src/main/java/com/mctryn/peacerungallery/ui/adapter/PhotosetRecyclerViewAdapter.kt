package com.mctryn.peacerungallery.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mctryn.peacerungallery.R
import com.mctryn.peacerungallery.model.data.photoset.local.PhotosetItemLocal
import com.mctryn.peacerungallery.utils.loadImageFromLink

class PhotosetRecyclerViewAdapter(
    private var values: List<PhotosetItemLocal>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<PhotosetRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_photoset_list_card, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.titleTextView.text = item.title
        holder.imageView.loadImageFromLink(item.getImageLink())
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(
        view: View,
        private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val imageView: ImageView = view.findViewById(R.id.imageView)

        init {
            view.setOnClickListener(this)
        }

        override fun toString(): String {
            return super.toString() + " '" + titleTextView.text + "'"
        }

        override fun onClick(clickedView: View) {
            onItemClickListener.onItemClicked(adapterPosition)
        }
    }

    fun addNewItem(newItems: List<PhotosetItemLocal>) {
        values = newItems
        notifyDataSetChanged()
    }

    fun interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }
}