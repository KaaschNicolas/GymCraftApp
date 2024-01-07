package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.adapters.SubscriptionListAdapter
import com.example.myapplication.daos.TariffDao
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityMembershipBinding
import com.example.myapplication.databinding.ActivityMySupscriptionsBinding

class MySubscriptionActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: SubscriptionListAdapter
    private lateinit var tariffDao: TariffDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_supscriptions);

        listView = findViewById(R.id.subscriptionLv)
        adapter = SubscriptionListAdapter(this, ArrayList())
        listView.adapter = adapter
    }
}