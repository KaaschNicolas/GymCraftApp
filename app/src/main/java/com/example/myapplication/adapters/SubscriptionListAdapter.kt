package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.R
import com.example.myapplication.models.Course
import com.example.myapplication.models.Tariff
import java.text.SimpleDateFormat

class SubscriptionListAdapter(
    private val context: FragmentActivity,
    private val arrayList: ArrayList<Tariff>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.subscription_list_item, null)

        //val imageView: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.subscriptionName)
        val message: TextView = view.findViewById(R.id.message)

        /**
        val formatter = SimpleDateFormat("dd.MM")
        val date = arrayList[position].date
        val current = formatter.format(date)
         */

        val msgCost: TextView = view.findViewById(R.id.msgCost)

        //imageView.setImageResource(arrayList[position].imageId)

        //change id to names somewhere in the code
        name.text = arrayList[position].id.toString()
        message.text = arrayList[position].description
        msgCost.text = arrayList[position].price.toString()

        //TODO: id to name and adding pictures maybe?
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