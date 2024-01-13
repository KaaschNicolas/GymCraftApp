package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.models.Customer
import com.example.myapplication.models.Tariff
import com.example.myapplication.viewmodels.CustomerViewModel

class SubscriptionListAdapter(
    //get context from the activity
    private val context: Context,
    private val arrayList: ArrayList<Tariff>,
    private val currentUser: Int
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.subscription_list_item, null)

        //val imageView: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.subscriptionName)
        val bookStatus: TextView = view.findViewById(R.id.bookStatus)

        val msgCost: TextView = view.findViewById(R.id.msgCost)

        //imageView.setImageResource(arrayList[position].imageId)

        name.text = arrayList[position].description
        msgCost.text = arrayList[position].price.toString()

        //TODO: make this dependent on who is logged in not "1", dunno how to inject the user that is logged in here
        if (arrayList[position].customerId == currentUser) {
            bookStatus.text = "Erworben"
        } else {
            bookStatus.text = "Kostenpflichtig Erwerben"
        }

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