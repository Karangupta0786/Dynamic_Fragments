package com.example.dynamicfragments.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicfragments.Adapter.GridAdapter
import com.example.dynamicfragments.ApiInterface
import com.example.dynamicfragments.Data.GridData
import com.example.dynamicfragments.Data.GridDataItem
import com.example.dynamicfragments.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class GridFragment : Fragment() {

    lateinit var fl_container:FrameLayout
    lateinit var grid_recycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //start work here..
//         val gridAdapter = GridAdapter()
        getNumbers()

    }

    private fun getNumbers() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build()
            .create(ApiInterface::class.java)

        val retrofitBuilder = retrofit.getGridData()
        retrofitBuilder.enqueue(object : Callback<GridData?> {
            override fun onResponse(call: Call<GridData?>, response: Response<GridData?>) {
                if (response.isSuccessful && isAdded){
                    Toast.makeText(requireContext(), "Mission succeed", Toast.LENGTH_SHORT).show()
                    val data = response.body()

                    grid_recycler = view!!.findViewById(R.id.grid_recycler_view)

                    grid_recycler.layoutManager = GridLayoutManager(requireContext(),3)
                    grid_recycler.setHasFixedSize(true)
                    val gridAdapter = GridAdapter(data as GridData,requireContext())
                    grid_recycler.adapter = gridAdapter
                    gridAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<GridData?>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}