package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.adapters.SubscriptionListAdapter
import com.example.myapplication.databinding.ActivityMySupscriptionsBinding
import com.example.myapplication.models.Tariff
import com.example.myapplication.viewmodel.MySubscriptionsViewModel
import com.example.myapplication.viewmodels.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MySubscriptionActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: SubscriptionListAdapter
    private lateinit var binding: ActivityMySupscriptionsBinding
    private lateinit var viewModel: MySubscriptionsViewModel
    private lateinit var customerViewModel: CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMySupscriptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MySubscriptionsViewModel::class.java]
        var tariffs = viewModel.getTariffs()

        customerViewModel = ViewModelProvider(this)[CustomerViewModel::class.java]
        var currentUser = customerViewModel.getUser().id

        Log.i("TariffListFragment", "${tariffs?.count()}")
        //Lade die listview bei erstellung der Activity
        listView = findViewById(R.id.subscriptionLv)
        // Dem Adapter den Aktivitätskontext, eine ArrayList aller Tarife und den aktuell angemeldeten Benutzer bereitstellen
        adapter = SubscriptionListAdapter(this, ArrayList(tariffs), currentUser)
        listView.adapter = adapter
    }

    private fun updateListView(tariffs: List<Tariff>) {
        // Update your ListView adapter with the new data
        //adapter.(tariffs)
    }
}