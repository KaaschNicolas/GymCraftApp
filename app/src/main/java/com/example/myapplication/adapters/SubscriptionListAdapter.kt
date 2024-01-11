package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.models.Tariff

class SubscriptionListAdapter(
    private val context: Context, //maybe so
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

        name.text = arrayList[position].description
        //message.text no need for message
        msgCost.text = arrayList[position].price.toString()

        //TODO: id to name and adding pictures maybe??
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