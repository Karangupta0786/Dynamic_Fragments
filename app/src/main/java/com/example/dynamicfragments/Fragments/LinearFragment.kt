package com.example.dynamicfragments.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.dynamicfragments.Adapter.ProductAdapter
import com.example.dynamicfragments.Data.ProductData
import com.example.dynamicfragments.R
import com.example.dynamicfragments.RetrofitBuilderElectronics
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class LinearFragment : Fragment() {
    lateinit var progressBar: ProgressBar
    lateinit var recycler : RecyclerView

    val url = "https://dummyjson.com/products"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_linear, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //start work here..
//        recyclerView = view.findViewById(R.id.recycler_product)
        progressBar = view.findViewById<ProgressBar>(R.id.progress_product)
        progressBar.isVisible = true

        recycler = view.findViewById(R.id.recycler_product)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.setHasFixedSize(true)

        getProducts()

    }

    private fun getProducts() {

        val retrofit = RetrofitBuilderElectronics.retrofit.getProduct()
        retrofit.enqueue(object : Callback<ProductData?> {
            override fun onResponse(call: Call<ProductData?>, response: Response<ProductData?>) {
                val data = response.body()!!
                val prodList = data.products
                Toast.makeText(requireContext(), "Mission succeed", Toast.LENGTH_SHORT).show()
                if (response.isSuccessful){
                    progressBar.isVisible = false
                    val productAdapter = ProductAdapter(prodList,requireContext())
                    recycler.adapter = productAdapter
                    productAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ProductData?>, t: Throwable) {
                progressBar.isVisible = false
                Toast.makeText(requireContext(), t.localizedMessage.toString(), Toast.LENGTH_SHORT).show()
            }
        })


    }
}