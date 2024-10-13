package com.example.mydicodingevent.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mydicodingevent.data.response.ListEventsItem
import com.example.mydicodingevent.databinding.ItemListEventFinishedBinding
import com.example.mydicodingevent.databinding.ItemListEventUpcomingBinding

class HomeUpcomingEventAdapter(private val onItemClick: (String) -> Unit) :
    ListAdapter<ListEventsItem, HomeUpcomingEventAdapter.EventViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemListEventUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
        holder.itemView.setOnClickListener {
            event.id.toString().let { id -> onItemClick(id) }
        }
    }

    class EventViewHolder(private val binding: ItemListEventUpcomingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: ListEventsItem) {
            with(binding) {
                tvUpcomingItemName.text = event.name
                Glide.with(binding.root.context)
                    .load(event.imageLogo)
                    .into(binding.imgItemPhoto)
            }
        }
    }

    private class EventDiffCallback : DiffUtil.ItemCallback<ListEventsItem>() {
        override fun areItemsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
            return oldItem == newItem
        }
    }
}