package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import androidx.activity.ComponentActivity
import android.view.View
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

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
            if(username == "user" && password == "password123") {
                Toast.makeText(this@MainActivity, "Anmeldung erfolgreich", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Anmeldung fehlgeschlagen.", Toast.LENGTH_SHORT).show()
            }
        })

        //setContent {
            //MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                //Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                   // Greeting("Android")
               // }
           // }
        //}
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
  //  Text(
     //       text = "Hello $name!",
     //       modifier = modifier
  //  )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
 //   MyApplicationTheme {
   //     Greeting("Android")
  //  }
//}