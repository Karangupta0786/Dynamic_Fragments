package com.example.dynamicfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.dynamicfragments.Fragments.GridFragment
import com.example.dynamicfragments.Fragments.LinearFragment
import com.example.dynamicfragments.Fragments.MemeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_nav)

        replaceWithFragment(GridFragment())
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.grid_recycle -> replaceWithFragment(GridFragment())
                R.id.meme -> replaceWithFragment(MemeFragment())
                R.id.linear -> replaceWithFragment(LinearFragment())
            }
            true
        }

    }

    private fun replaceWithFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_container,fragment)
        fragmentTransaction.commit()
    }
}