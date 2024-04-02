package com.example.koleo.presentation.feature.plan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.koleo.data.entities.Station
import com.example.koleo.databinding.ItemStationBinding

class PlanAdapter(
    var items: List<Station>,
    var onStationSelected: (Station) -> Unit,
) : ListAdapter<Station, PlanAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = ViewHolder(
        ItemStationBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false,
        )
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = with(viewHolder.binding) {
        tvStation.text = items[position].name
        clRoot.setOnClickListener {
            onStationSelected(items[position])
        }
    }

    override fun getItemCount() = items.size

    class ViewHolder(val binding: ItemStationBinding) : RecyclerView.ViewHolder(binding.root)
    class DiffCallback : DiffUtil.ItemCallback<Station>() {
        override fun areItemsTheSame(oldItem: Station, newItem: Station): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Station, newItem: Station): Boolean {
            return oldItem == newItem // TODO: Verify correctness
        }
    }
}