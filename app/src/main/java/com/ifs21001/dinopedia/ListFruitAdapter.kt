package com.ifs21001.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21001.dinopedia.databinding.ItemRowFruitBinding
class ListFruitAdapter(private val listFruit: ArrayList<Fruit>) :
    RecyclerView.Adapter<ListFruitAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback:
                               OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemRowFruitBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val fruit = listFruit[position]
        holder.binding.ivItemFruit.setImageResource(fruit.icon)
        holder.binding.tvItemFruit.text = fruit.name
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listFruit[holder.adapterPosition])
        }
    }
    override fun getItemCount(): Int = listFruit.size
    class ListViewHolder(var binding: ItemRowFruitBinding) :
        RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: Fruit)
    }
}

