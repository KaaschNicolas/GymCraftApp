package com.example.myapplication

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
        val dateTextView = findViewById<TextView>(R.id.date)
        val subscriptionButton = findViewById<Button>(R.id.subscriptionButton)


        course?.let {
            imageView.id= course.imageId
            courseNameTextView.text = course.name
            descriptionTextView.text = course.description
            courseMaxParticipantsTextView.text = course.maxNumberOfEntrants.toString()
            val formatter = SimpleDateFormat("dd.MM")
            dateTextView.text = formatter.format(course.date)
        }
        Log.i("checkMappingExists", viewModel?.checkMappingExists(1, course?.id).toString())
        if (viewModel?.checkMappingExists(1, course?.id) !== null){
            subscriptionButton.text = "Abmelden"
        } else {
            subscriptionButton.text = "Anmelden"
        }
         subscriptionButton.setOnClickListener {
             val check = viewModel?.subscribeunsubscribe(1, course?.id)
             if (check == true){
                 val myToast = Toast.makeText(applicationContext, "Sie wurden erfolgreich angemeldet", Toast.LENGTH_SHORT)
                 myToast.show() // Show the toast
                // Set a timer to cancel the toast after 5 seconds (5000ms)
                 Handler(Looper.getMainLooper()).postDelayed({
                     myToast.cancel() // This will dismiss the toast
                 }, 5000)
             } else {
                 val myToast = Toast.makeText(applicationContext, "Sie wurden erfolgreich abgemeldet", Toast.LENGTH_SHORT)
                 myToast.show()
                 Handler(Looper.getMainLooper()).postDelayed({
                     myToast.cancel()
                 }, 5000)
             }

         }

        val callback = this.onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
}