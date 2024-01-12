package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMembershipBinding
import com.example.myapplication.models.Customer
import com.example.myapplication.viewmodels.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class MembershipActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMembershipBinding
    private var viewModel: CustomerViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMembershipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        val customer: Customer? = viewModel?.getUser()

        Log.i("User", "${customer?.firstName}")

        if (customer != null) {
            binding.memberSince.text = "Mitglied seit: \n" + viewModel!!.dateToReadableDate(customer.memberSince)
            binding.membershipNumber.text = "Deine Mitgliedsschaftsnummer: \n" + (customer.memberNumber)
        }


        //activity swap to MySubscriptionActivity
        binding.membershipChangeButton.setOnClickListener {

            val intent = Intent(this, MySubscriptionActivity::class.java)
            startActivity(intent)
        }

    }

}