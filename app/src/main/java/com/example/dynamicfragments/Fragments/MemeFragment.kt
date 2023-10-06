package com.example.dynamicfragments.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.dynamicfragments.R
import org.json.JSONObject

class MemeFragment : Fragment() {

    val url = "https://meme-api.com/gimme"
    lateinit var meme:ImageView

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
        return inflater.inflate(R.layout.fragment_meme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ProgressBar>(R.id.progress_circular).isVisible = true
        meme = view.findViewById(R.id.img_meme)
        getMeme()
        view.findViewById<Button>(R.id.btn_meme).setOnClickListener {
            view.findViewById<ProgressBar>(R.id.progress_circular).isVisible = true
            getMeme()
        }

    }

    private fun getMeme() {
        //start work here..
        
// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(requireContext())

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.

                val responseObject = JSONObject(response)

                responseObject.get("postLink")
                view?.findViewById<TextView>(R.id.title)?.text = responseObject.getString("title")

                Glide.with(requireActivity()).load(responseObject.get("url")).into(meme)

                view?.findViewById<ProgressBar>(R.id.progress_circular)?.isVisible = false
                
            },
            { error->
                Toast.makeText(requireContext(), "${error.localizedMessage}", Toast.LENGTH_SHORT).show()
                
            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}