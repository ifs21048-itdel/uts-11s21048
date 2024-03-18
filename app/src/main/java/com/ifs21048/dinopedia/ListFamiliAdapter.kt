package com.ifs21048.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21048.dinopedia.databinding.ItemRowFamiliBinding

class ListFamiliAdapter(private val listFamili: ArrayList<Famili>) :
    RecyclerView.Adapter<ListFamiliAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowFamiliBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val famili = listFamili[position]
        holder.binding.familiItem.setImageResource(famili.gambar)
        holder.binding.tvItemFamili.text = famili.famili
        holder.itemView.setOnClickListener {
            onItemClickCallback .onItemClicked(listFamili[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listFamili.size
    class ListViewHolder(var binding: ItemRowFamiliBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Famili)
    }
}