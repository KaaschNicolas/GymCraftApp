package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.models.Course
import com.example.myapplication.viewmodel.CourseViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
@AndroidEntryPoint
class CourseDetailActivity : AppCompatActivity() {

    private var viewModel: CourseViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("CourseDetailAcitvity", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        viewModel = ViewModelProvider(this).get(CourseViewModel::class.java)
        val course: Course? = viewModel?.getCourse(intent.getIntExtra("course", 1))

        val imageView = findViewById<ImageView>(R.id.image)
        val courseNameTextView = findViewById<TextView>(R.id.courseName)
        val descriptionTextView = findViewById<TextView>(R.id.description)
        val courseMaxParticipantsTextView = findViewById<TextView>(R.id.courseMaxParticipants)
        val currentParticipantsTextView = findViewById<TextView>(R.id.currentParticipants)
        val dateTextView = findViewById<TextView>(R.id.date)
        val subscriptionButton = findViewById<Button>(R.id.subscriptionButton)
        var leftPlaces = 1

        course?.let {
            imageView.id= it.imageId
            courseNameTextView.text = it.name
            descriptionTextView.text = it.description
            courseMaxParticipantsTextView.text = "Maximale Teilnehmeranzahl: ${it.maxNumberOfEntrants}"
            val formatter = SimpleDateFormat("dd.MM.YYYY")
            dateTextView.text = "Datum: ${formatter.format(it.date)}"
            viewModel?.let{
                leftPlaces = course.maxNumberOfEntrants - it.countParticipants(course.id)
                currentParticipantsTextView.text= "Anzahl freier Plätze: ${course.maxNumberOfEntrants - it.countParticipants(course.id)}"
            }
        }
        Log.i("checkMappingExists", viewModel?.checkMappingExists(course?.id).toString())
        if (viewModel?.checkMappingExists(course?.id) != null){
            subscriptionButton.setBackgroundColor(Color.RED)
            subscriptionButton.text = "Abmelden"
        } else if (leftPlaces == 0){
            subscriptionButton.isEnabled = false
        } else {
            subscriptionButton.text = "Anmelden"
        }
         subscriptionButton.setOnClickListener {
             val check = viewModel?.subscribeunsubscribe(course?.id)
             if (check == true){
                 val myToast = Toast.makeText(applicationContext, "Sie wurden erfolgreich abgemeldet", Toast.LENGTH_SHORT)
                 myToast.show() // Show the toast
                // Set a timer to cancel the toast after 5 seconds (5000ms)
                 Handler(Looper.getMainLooper()).postDelayed({
                     myToast.cancel() // This will dismiss the toast
                 }, 5000)
                 subscriptionButton.setBackgroundColor(Color.rgb(187, 134, 252))
                 subscriptionButton.text = "Anmelden"
             } else {
                 val myToast = Toast.makeText(applicationContext, "Sie wurden erfolgreich angemeldet", Toast.LENGTH_SHORT)
                 myToast.show()
                 Handler(Looper.getMainLooper()).postDelayed({
                     myToast.cancel()
                 }, 5000)
                 subscriptionButton.setBackgroundColor(Color.RED)
                 subscriptionButton.text = "Abmelden"
             }
             course?.let{
                 viewModel?.let {
                     currentParticipantsTextView.text= "Anzahl freier Plätze: ${course.maxNumberOfEntrants - it.countParticipants(course.id)}"
                 }
             }
         }

        val callback = this.onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
}