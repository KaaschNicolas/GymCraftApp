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
