package com.example.movieapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemListMovieBinding

class MyCustomAdapter(val list: List<Malumot>) : RecyclerView.Adapter<MyCustomAdapter.ViewHolder>(){
    private var itemEditRemove: RecycleViewEditRemove? =null

    inner class ViewHolder(val binding: ItemListMovieBinding):RecyclerView.ViewHolder(binding.root){
        fun binds(malumot:Malumot){
            binding.name.text= malumot.name
            binding.about.text= malumot.about
            binding.time.text= malumot.time
        }
        init {
            binding.delete.setOnClickListener{
                itemEditRemove?.removeItem(adapterPosition)
            }
            binding.edit.setOnClickListener{
                itemEditRemove?.editItem(adapterPosition)
            }
        }
    }
    fun setListener(listener: RecycleViewEditRemove){
        itemEditRemove =listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemListMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binds(list[position])
    }
}
