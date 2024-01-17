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

        //Setzen der jeweiligen Werte für den angeklickten Kurs

        course?.let {
            imageView.id= it.imageId
            courseNameTextView.text = it.name
            descriptionTextView.text = it.description
            courseMaxParticipantsTextView.text = "Maximale Teilnehmeranzahl: ${it.maxNumberOfEntrants}"
            val formatter = SimpleDateFormat("dd.MM.YYYY HH:mm")
            dateTextView.text = "Datum: ${formatter.format(it.date)}"
            imageView.setImageResource(it.imageId)
            viewModel?.let{
                leftPlaces = course.maxNumberOfEntrants - it.countParticipants(course.id)
                currentParticipantsTextView.text= "Anzahl freier Plätze: ${course.maxNumberOfEntrants - it.countParticipants(course.id)}"
            }
        }
        Log.i("checkMappingExists", viewModel?.checkMappingExists(course?.id).toString())

        //Prüfung, ob der angemeldete Nutzer beim angeklickten Kurs angemeldet ist.
        //Falls der Kurs ausgebucht ist, wird der Button ausgegraut
        if (viewModel?.checkMappingExists(course?.id) != null){
            subscriptionButton.setBackgroundColor(Color.RED)
            subscriptionButton.text = "Abmelden"
        } else if (leftPlaces == 0){
            subscriptionButton.isEnabled = false
        } else {
            subscriptionButton.text = "Anmelden"
        }
        //Button dient entweder der An- oder Abmeldung. Je nach dem wird er entsprechend markiert und bei klicken des Button eine Mitteilung am unteren Rand des Bildschirms angezeigt
        //Nach dem Klick wird die aktuelle Teilnehmerzahl und der Button angepasst
         subscriptionButton.setOnClickListener {
             val check = viewModel?.subscribeunsubscribe(course?.id)
             if (check == true){
                 //Wenn der User bisher angemeldet war
                 val myToast = Toast.makeText(applicationContext, "Sie wurden erfolgreich abgemeldet", Toast.LENGTH_SHORT)
                 myToast.show() // Zeige den Toast/Die Mitteilung
                // Setze einen Timer um die Mitteilung nach 5 Sek wieder versdhwinden zu lassen (5000ms)
                 Handler(Looper.getMainLooper()).postDelayed({
                     myToast.cancel() // Zeige die Mitteilung nicht mehr
                 }, 5000)
                 subscriptionButton.setBackgroundColor(Color.rgb(187, 134, 252))
                 subscriptionButton.text = "Anmelden"
             } else {
                 //wenn der User bisher abgemeldet war
                 val myToast = Toast.makeText(applicationContext, "Sie wurden erfolgreich angemeldet", Toast.LENGTH_SHORT)
                 myToast.show()
                 Handler(Looper.getMainLooper()).postDelayed({
                     myToast.cancel()
                 }, 5000)
                 subscriptionButton.setBackgroundColor(Color.RED)
                 subscriptionButton.text = "Abmelden"
             }
             //Aktualisierung der Anzahl freier Plätze
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