package com.example.myapplication.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.R
import com.example.myapplication.models.Course

class CourseListAdapter(
    private val context: FragmentActivity,
    private val arrayList: ArrayList<Course>
) : ArrayAdapter<Course>(context, R.layout.course_list_item) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.course_list_item, null)

        val imageView: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.courseName)
        val message: TextView = view.findViewById(R.id.message)
        val msgTime: TextView = view.findViewById(R.id.msgTime)

        imageView.setImageResource(arrayList[position].imageId)
        name.text = arrayList[position].name
        message.text = arrayList[position].description
        msgTime.text = arrayList[position].date.toString()

        return view
    }
}