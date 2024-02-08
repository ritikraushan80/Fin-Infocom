package com.fininfocom.fininfocomapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fininfocom.fininfocomapplication.Model.UserData
import com.fininfocom.fininfocomapplication.databinding.UserItemBinding

/**
 * Created by Ritik on 2/8/2024.
 */
class ListAdapter( private val list: List<UserData>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    lateinit var binding: UserItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        binding.name.text = item.name
        binding.age.text = "Age : ${item.age}"
        binding.city.text = item.city

    }


    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: UserItemBinding

        init {
            this.binding = binding
        }
    }
}