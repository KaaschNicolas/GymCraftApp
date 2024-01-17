package com.example.myapplication

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import com.example.myapplication.adapters.CourseListAdapter
import com.example.myapplication.models.Course
import com.example.myapplication.viewmodel.CourseListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class CourseListFragment(
    var lv: ListView? = null
) : Fragment(R.layout.fragment_course_list) {

    private var viewModel: CourseListViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.i("CourseListFragment", "onCreateView")
        viewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)

        val view: View = inflater.inflate(R.layout.fragment_course_list, container, false)
        val lv = view.findViewById<ListView>(R.id.courseLv)
        val searchView = view.findViewById<SearchView>(R.id.courseListSearchView)
        val filterAll = view.findViewById<Button>(R.id.allCourses)
        val filterSubscribed = view.findViewById<Button>(R.id.subscribed)
        val filterStrengthTraining = view.findViewById<Button>(R.id.strengthTraining)
        val filterCardio = view.findViewById<Button>(R.id.cardio)
        val filterStretching = view.findViewById<Button>(R.id.stretching)

        var courses = viewModel?.getCourses()
        var myCourses = viewModel?.getMyCourses()

        Log.i("CourseListFragment", "${courses?.count()}")
        //Anzeige aller existierenden Kurse
        val activityContext = activity
        if (activityContext != null) {
            val arrayAdapter = CourseListAdapter(activityContext, ArrayList(courses))
            lv.adapter = arrayAdapter
        }
        //Leitet bei Klick auf einen Kurs auf die jeweilige Kursdetailseite weiter und gibt die ID des Kurses als Information an die CourseDetailActivity mit
        lv.setOnItemClickListener { parent, view, position, id ->
            val element = courses?.get(position)
            //navigate to next activity/fragment here
            val intent = Intent(activity, CourseDetailActivity::class.java)
            intent.putExtra("course", element?.id)
            startActivity(intent)
        }
        //Implementiert die Suche.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                return false
            }
            // Es wird ein Array mit Kursen befüllt, die die in der Suchleiste eingegebenen Buchstabenfolgen enthalten
            //Dabei wird aus den Kursen befüllt, die bereits gefiltert wurde, falls ein Filter gesetzt wurde
            override fun onQueryTextChange(s: String): Boolean {
                val searchedCourses: ArrayList<Course> = ArrayList<Course>()
                courses?.let{
                    for (course in it) {
                        if (course.name.lowercase().contains(s.lowercase(Locale.getDefault()))) {
                            searchedCourses.add(course)
                        }
                    }
                }
                if (activityContext != null) {
                    val arrayAdapter = CourseListAdapter(activityContext, ArrayList(searchedCourses))
                    lv.adapter = arrayAdapter
                }
                return false
            }
        })
        //Diese Funktion wird aufgerufen, wenn einer der Filter nach den Tags (Cardio, Stretching und Krafttraining) angeklickt wurde
        fun showFilteredCourses(tagId: Int){
            courses = viewModel?.getCoursesByTagId(tagId)
            if (activityContext != null) {
                val arrayAdapter = CourseListAdapter(activityContext, ArrayList(courses))
                lv.adapter = arrayAdapter
            }
        }
        //Selektiert alle verfügbaren Kurse und zeigt diese an
        filterAll.setOnClickListener(){
            courses = viewModel?.getCourses()
            if (activityContext != null) {
                val arrayAdapter = CourseListAdapter(activityContext, ArrayList(courses))
                lv.adapter = arrayAdapter
            }
        }
        //Selektiert alle Kurse, zu denen der Nutzer angemeldet ist und zeigt diese an
        filterSubscribed.setOnClickListener(){
            myCourses = viewModel?.getMyCourses()
            if (activityContext != null) {
                val arrayAdapter = CourseListAdapter(activityContext, ArrayList(myCourses))
                lv.adapter = arrayAdapter
            }
        }
        //Rufen die Funktion showFilteredCourses mit der jeweiligen Tag Id die zu dem Button gehört
        filterStrengthTraining.setOnClickListener(){
            showFilteredCourses(1)
        }
        filterCardio.setOnClickListener(){
            showFilteredCourses(2)
        }
        filterStretching.setOnClickListener(){
            showFilteredCourses(3)
        }
        return view
    }
}