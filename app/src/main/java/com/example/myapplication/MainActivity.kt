package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import androidx.activity.ComponentActivity
import android.view.View
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
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
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

class MainActivity : ComponentActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Intialisierung der Views
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginBtn = findViewById(R.id.loginBtn)

        //Click Event für Login
        loginBtn.setOnClickListener(View.OnClickListener {
            val username = username.text.toString()
            val password = password.text.toString()

            TODO("Placeholder ersetzten")
            //if (username == "user" && password == "password123") {
            // Toast.makeText(this@MainActivity, "Anmeldung erfolgreich", Toast.LENGTH_SHORT).show();
            openOverview();
            // } else {
            //    Toast.makeText(this@MainActivity, "Anmeldung fehlgeschlagen.", Toast.LENGTH_SHORT).show()
            // }
        })
    }
    fun openOverview() {
        val intent = Intent(this, OverviewActivity::class.java)
        startActivity(intent)
    }

}
