package com.example.dynamicfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.dynamicfragments.Data.Product
import com.google.gson.Gson

class ProductActivity : AppCompatActivity() {
    lateinit var product_detail_Title: TextView
    lateinit var product_detail_details: TextView
    lateinit var product_detail_price: TextView
    lateinit var imgProduct_detail: ImageView
    lateinit var btn_order: Button
    lateinit var iv_back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
//        Toast.makeText(applicationContext, intent.getStringExtra("product"), Toast.LENGTH_SHORT).show()

        val data = Gson()?.fromJson(intent.getStringExtra("product")!!, Product::class.java)
        Toast.makeText(applicationContext, data?.description, Toast.LENGTH_SHORT).show()
        Toast.makeText(applicationContext, data?.price.toString(), Toast.LENGTH_SHORT).show()
        Toast.makeText(applicationContext, data?.title, Toast.LENGTH_SHORT).show()
        Toast.makeText(applicationContext, data?.thumbnail, Toast.LENGTH_SHORT).show()

        product_detail_Title = findViewById(R.id.product_detail_Title)
        product_detail_details = findViewById(R.id.product_detail_details)
        product_detail_price = findViewById(R.id.product_detail_price)
        imgProduct_detail = findViewById(R.id.imgProduct_detail)
        btn_order = findViewById(R.id.btn_order)
        iv_back = findViewById(R.id.back)

        product_detail_Title.text = data?.title
        product_detail_details.text = data?.description
        product_detail_price.text = "${data?.price}RS"
        val url = data?.thumbnail
        btn_order.setOnClickListener {
            Toast.makeText(applicationContext, "Order placed", Toast.LENGTH_SHORT).show()
        }
        iv_back.setOnClickListener {
            onBackPressed()
        }

        Glide.with(applicationContext).load(url).into(imgProduct_detail)

//        Picasso.get().load(url).into(imgProduct_detail)



    }
}