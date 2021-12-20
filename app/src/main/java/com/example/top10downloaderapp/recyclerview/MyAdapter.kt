package com.example.top10downloaderapp.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.top10downloaderapp.databinding.RowBinding
import com.example.top10downloaderapp.model.Application
import java.net.URL
import android.graphics.BitmapFactory




class MyAdapter(var applications: ArrayList<Application>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    class MyHolder(val binding: RowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val application = applications[position]

        holder.binding.apply {
            title.text = application.name
            // image.setImageBitmap(application.image)

        }
    }

    override fun getItemCount(): Int = applications.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: ArrayList<Application>){
        applications = newList
        notifyDataSetChanged()
    }
}