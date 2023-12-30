package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.adapters.TabAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Kurse"
                    1 -> tab.text = "Mein Profil"
                    2 -> tab.text = "Studio"
                }
            }

        binding.viewPager.adapter = TabAdapter(this)
        tabLayoutMediator.attach()
    }


}

