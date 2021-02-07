package com.mctryn.peacerungallery.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mctryn.peacerungallery.R
import com.mctryn.peacerungallery.model.data.photosetDetail.local.PhotosetDetailItemLocal
import com.mctryn.peacerungallery.utils.loadDetailImageFromLink
import com.stfalcon.imageviewer.StfalconImageViewer

@Suppress("NAME_SHADOWING")
class DetailRecyclerViewAdapter(
    private var values: List<PhotosetDetailItemLocal>
) : RecyclerView.Adapter<DetailRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_detail_list_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.imageView.loadDetailImageFromLink(item.getImageLink())

        holder.imageView.setOnClickListener { view ->
            StfalconImageViewer.Builder(
                view.context,
                values
            ) { view: ImageView, values -> view.loadDetailImageFromLink(values.getImageLink()) }
                .withStartPosition(position)
                .show(true)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewDetail)
    }

    fun addNewItem(newItems: List<PhotosetDetailItemLocal>) {
        values = newItems
        notifyDataSetChanged()
    }
}

