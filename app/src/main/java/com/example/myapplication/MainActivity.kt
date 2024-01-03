package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.adapters.TabAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.Course
import com.example.myapplication.repositories.CourseRepository
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var profileButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        profileButton = findViewById(R.id.profile)

        profileButton.setOnClickListener({
            val profilePage = Intent(this, ActivityProfile::class.java)
            startActivity(profilePage)
            finish()
        })
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "News"
                1 -> tab.text = "Kurs"
                2 -> tab.text = "Studio"
                //3 -> tab.text = "Mein Profil"
            }
        }


        binding.viewPager.adapter = TabAdapter(this)
        tabLayoutMediator.attach()
    }

}

