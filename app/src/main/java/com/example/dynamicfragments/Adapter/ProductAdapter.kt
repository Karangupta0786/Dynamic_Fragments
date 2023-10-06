package com.example.dynamicfragments.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dynamicfragments.Data.Product
import com.example.dynamicfragments.ProductActivity
import com.example.dynamicfragments.R
import com.google.gson.Gson

class ProductAdapter(val productList:List<Product>,val applicationContext:Context): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    class ProductHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val img_product:ImageView = itemView.findViewById(R.id.img_product)
        val title_product:TextView = itemView.findViewById(R.id.title_product)
        val price_product:TextView = itemView.findViewById(R.id.Price_product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val inflater = LayoutInflater.from(applicationContext)
        val view = inflater.inflate(R.layout.item_linear_layout,parent,false)
        return ProductHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val currentData = productList[position]
        holder.title_product.text = currentData.title
        holder.price_product.text = currentData.price.toString()
        Glide.with(applicationContext).load(currentData.thumbnail).into(holder.img_product)

        holder.itemView.setOnClickListener {
            val intent = Intent(applicationContext, ProductActivity::class.java)
            intent.putExtra("product", Gson().toJson(productList[position]))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            applicationContext.startActivity(intent)
        }
    }
}