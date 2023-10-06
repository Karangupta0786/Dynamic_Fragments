package com.example.dynamicfragments.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicfragments.Data.GridData
import com.example.dynamicfragments.R

class GridAdapter(val numberList:GridData,val applicationContext:Context): RecyclerView.Adapter<GridAdapter.GridHolder>() {
    class GridHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val number: TextView = itemView.findViewById(R.id.txt_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHolder {
        val inflater = LayoutInflater.from(applicationContext)
        val view = inflater.inflate(R.layout.item_grid_layout,parent,false)
        return GridHolder(view)
    }

    override fun getItemCount(): Int {
        return numberList.size
    }

    override fun onBindViewHolder(holder: GridHolder, position: Int) {
        val currentData = numberList[position]
        holder.number.text = currentData.id.toString()
    }
}