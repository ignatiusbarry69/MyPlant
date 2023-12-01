package com.dicoding.myplants.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.myplants.core.R
import com.dicoding.myplants.core.databinding.ListItemPlantBinding
import com.dicoding.myplants.core.domain.model.Plant

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.ListViewHolder>() {

    private var listData = ArrayList<Plant>()
    var onItemClick: ((Plant) -> Unit)? = null

    fun setData(newListData: List<Plant>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_plant, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemPlantBinding.bind(itemView)
        fun bind(data: Plant) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .into(plantItemImage)
                plantItemTitle.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}