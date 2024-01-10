package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.adapters.TabAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.Course
import com.example.myapplication.repositories.CourseRepository
import com.example.myapplication.services.CustomerService
import com.example.myapplication.viewmodel.CourseListViewModel
import com.example.myapplication.viewmodel.LoginViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var viewModel: LoginViewModel? = null

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel?.triggerDB()
        setContentView(R.layout.activity_login)

        //Intialisierung der Views
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginBtn = findViewById(R.id.loginBtn)

        //Click Event für Login
        loginBtn.setOnClickListener(View.OnClickListener {
            val username = username.text.toString()
            val password = password.text.toString()

            val isValidUser = checkLogin(username, password)

            if (isValidUser == true) {
                openOverview();
            } else {
                val toast = Toast.makeText(applicationContext, "Passwort nicht korrekt", Toast.LENGTH_SHORT)
                toast.show()
                Handler(Looper.getMainLooper()).postDelayed({
                    toast.cancel()
                }, 5000)
            }



            //TODO("Placeholder ersetzten")
            //if (username == "user" && password == "password123") {
            // Toast.makeText(this@MainActivity, "Anmeldung erfolgreich", Toast.LENGTH_SHORT).show();
            // } else {
            //    Toast.makeText(this@MainActivity, "Anmeldung fehlgeschlagen.", Toast.LENGTH_SHORT).show()
            // }
        })
    }

    private fun checkLogin(username: String, password: String) = viewModel?.checkLogin(password, username)

    private fun openOverview() {
        //Log.i("Login", "${customerService != null}")
        val intent = Intent(this, OverviewActivity::class.java)
        startActivity(intent)
    }


}