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
    // Den Kontext aus der Aktivität sowie den aktuellen Benutzer und eine Liste aller verfügbaren Tarife erhalten
    private val context: Context,
    private val arrayList: ArrayList<Tariff>,
    private val currentUser: Int
) : BaseAdapter() {

    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Den Inflater aus dem Kontext erhalten, der zur Aktivität gehört
        val inflater: LayoutInflater = LayoutInflater.from(context)
        // Ansicht der Listenelemente erhalten
        val view: View = inflater.inflate(R.layout.subscription_list_item, null)

        //setze die variablen für Namen, Status, Layout und kosten
        val name: TextView = view.findViewById(R.id.subscriptionName)
        val bookStatus: TextView = view.findViewById(R.id.bookStatus)
        val fullLayout: RelativeLayout = view.findViewById(R.id.fullLayout)
        val msgCost: TextView = view.findViewById(R.id.msgCost)

        // Beschreibung und Preis an die entsprechende Position der TextViews einfügen
        name.text = arrayList[position].description
        msgCost.text = arrayList[position].price.toString()

        // Überprüfen, ob die Kunden-ID mit der id des angemeldeten Benutzer übereinstimmt
        // Falls dies der Fall ist, den Status auf "Erworben" setzen und die Farbe festlegen
        // Andernfalls den Text auf "Kostenpflichtig Erwerben" setzen
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