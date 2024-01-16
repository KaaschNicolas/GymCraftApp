package com.example.myapplication.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.models.Customer
import com.example.myapplication.models.Tariff
import com.example.myapplication.viewmodels.CustomerViewModel

class SubscriptionListAdapter(
    //get context from the activity as well as the current user and a List of all available tariffs
    private val context: Context,
    private val arrayList: ArrayList<Tariff>,
    private val currentUser: Int
) : BaseAdapter() {

    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //get inflater from context which is the activity it belongs to
        val inflater: LayoutInflater = LayoutInflater.from(context)
        //get view of list items
        val view: View = inflater.inflate(R.layout.subscription_list_item, null)

        //set variables for name status, Layout and cost
        val name: TextView = view.findViewById(R.id.subscriptionName)
        val bookStatus: TextView = view.findViewById(R.id.bookStatus)
        val fullLayout: RelativeLayout = view.findViewById(R.id.fullLayout)
        val msgCost: TextView = view.findViewById(R.id.msgCost)

        //inject description and price to the according position of the textviews
        name.text = arrayList[position].description
        msgCost.text = arrayList[position].price.toString()

        //check if the customerid is the same as the current logged in user
        //if that is the case then set the status to "Erworben" and set the color to the main theme
        //else set text to "Kostenpflichtig Erwerben"
        if (arrayList[position].customerId == currentUser) {
            bookStatus.text = "Erworben"
            fullLayout.setBackgroundColor(R.color.colorPrimary)

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