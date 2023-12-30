package com.example.myapplication

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.myapplication.adapters.CourseListAdapter
import com.example.myapplication.models.Course
import com.example.myapplication.viewmodel.CourseListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CourseListFragment(
    var lv: ListView? = null
) : Fragment(R.layout.fragment_course_list) {

    private var viewModel: CourseListViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)

        var view: View = inflater.inflate(R.layout.fragment_course_list, container, false)
        var lv = view.findViewById<ListView>(R.id.courseLv)

        var courses = viewModel?.getCourses()

        val activityContext = activity

        if (activityContext != null) {
            var arrayAdapter = CourseListAdapter(activityContext, ArrayList(courses))
            lv.adapter = arrayAdapter
        }

        lv.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position)

            //navigate to next activity/fragment here
            val intent = Intent(getActivity(), CourseDetailActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}