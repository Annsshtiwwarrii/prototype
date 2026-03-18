package com.example.prototype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prototype.databinding.ItemSliderBinding

class SliderAdapter(
    private var list: List<dataClass>
) : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSliderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSliderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.binding.tvHeading.text = item.prompt
        holder.binding.tvDescription.text = item.short_description

        Glide.with(holder.itemView.context)
            .load(item)
            .into(holder.binding.ivImage)
    }

    fun updateData(newList: List<dataClass>) {
        list = newList
        notifyDataSetChanged()
    }
}