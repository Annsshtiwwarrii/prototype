package com.example.prototype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prototype.databinding.ItemSliderBinding

class Slideradapter(private var list:List<dataClass>
): RecyclerView.Adapter<Slideradapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemSliderBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSliderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvHeading.text = item.prompt
        Glide.with(holder.itemView.context)
            .load(item.image_url)
            .into(holder.binding.rvImage)
        }
    fun updateData(newList: List<dataClass>)
    {
        list = newList
        notifyDataSetChanged()
    }

}