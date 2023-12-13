package com.dicoding.myplants.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.myplants.core.R
import com.dicoding.myplants.core.databinding.ListItemPlantBinding
import com.dicoding.myplants.core.domain.model.Plant

class PlantAdapter : ListAdapter<Plant, PlantAdapter.ListViewHolder>(PlantDiffCallback()) {

    var onItemClick: ((Plant) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_plant, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
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
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.plantId == newItem.plantId
        }

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem == newItem
        }
    }
}
