package com.example.myapplication.adapters

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.R
import com.example.myapplication.models.Course
import java.text.SimpleDateFormat
import java.util.Date

//Adapter für die Kursliste, um Daten in den einzelnen Items in der Kursliste anzuzeigen
class CourseListAdapter(
    private val context: FragmentActivity,
    private val arrayList: ArrayList<Course>
) : BaseAdapter() {

    //setzt die Daten in den einzelnen Items der ViewList
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.course_list_item, null)

        val imageView: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.courseName)
        val message: TextView = view.findViewById(R.id.message)

        val formatter = SimpleDateFormat("dd.MM.YYYY")
        val date = arrayList[position].date
        val current = formatter.format(date)

        val msgTime: TextView = view.findViewById(R.id.msgTime)

        imageView.setImageResource(arrayList[position].imageId)
        name.text = arrayList[position].name
        message.text = arrayList[position].description
        msgTime.text = current

        return view
    }

    override fun getCount(): Int {
        return arrayList.count()
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return arrayList[position].hashCode().toLong()
    }
}