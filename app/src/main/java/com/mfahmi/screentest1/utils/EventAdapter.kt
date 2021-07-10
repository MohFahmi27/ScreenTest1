package com.mfahmi.screentest1.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfahmi.screentest1.data.dummy.Event
import com.mfahmi.screentest1.databinding.ItemsEventBinding

class EventAdapter(private val events: ArrayList<Event>) :
    RecyclerView.Adapter<EventAdapter.EventAdapterViewHolder>() {

    var onItemClick: ((Event) -> Unit)? = null

    inner class EventAdapterViewHolder(private val binding: ItemsEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(events: Event) {
            with(binding) {
                imgEvent.loadImageUsingGlide(events.img)
                tvNameEvent.text = events.name
                tvEventDate.text = events.date
            }
        }

        init {
            binding.root.setOnClickListener { onItemClick?.invoke(events[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapterViewHolder {
        return EventAdapterViewHolder(
            ItemsEventBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EventAdapterViewHolder, position: Int) =
        holder.bind(events[position])

    override fun getItemCount(): Int = events.size

    private fun ImageView.loadImageUsingGlide(urlPath: String) =
        Glide.with(this).load(urlPath).into(this)
}