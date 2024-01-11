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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MySubscriptionActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: SubscriptionListAdapter
    private lateinit var binding: ActivityMySupscriptionsBinding
    private lateinit var viewModel: MySubscriptionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMySupscriptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MySubscriptionsViewModel::class.java]
        var tariffs = viewModel.getTariffs()

        Log.i("TariffListFragment", "${tariffs?.count()}")
        listView = findViewById(R.id.subscriptionLv)
        adapter = SubscriptionListAdapter(this, ArrayList(tariffs))
        listView.adapter = adapter
    }

    private fun updateListView(tariffs: List<Tariff>) {
        // Update your ListView adapter with the new data
        //adapter.(tariffs)
    }
}