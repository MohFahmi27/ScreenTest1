package com.mfahmi.screentest1.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfahmi.screentest1.R
import com.mfahmi.screentest1.data.network.Guest
import com.mfahmi.screentest1.databinding.ItemsGuestBinding

class GuestAdapter :
    RecyclerView.Adapter<GuestAdapter.GuestAdapterViewHolder>() {
    var guests = ArrayList<Guest>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    var onItemClick: ((Guest) -> Unit)? = null

    inner class GuestAdapterViewHolder(private val binding: ItemsGuestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myGuest: Guest) {
            with(binding) {
                tvNameGuest.text =
                    itemView.context.getString(R.string.name_placeholder, myGuest.name)
                tvBirthdateGuest.text =
                    itemView.context.getString(R.string.birthdate_placeholder, myGuest.birthdate)
            }
        }

        init {
            binding.root.setOnClickListener { onItemClick?.invoke(guests[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestAdapterViewHolder {
        return GuestAdapterViewHolder(
            ItemsGuestBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GuestAdapterViewHolder, position: Int) =
        holder.bind(guests[position])

    override fun getItemCount(): Int = guests.size
}