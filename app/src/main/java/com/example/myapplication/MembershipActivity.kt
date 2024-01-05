package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMembershipBinding

class MembershipActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMembershipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMembershipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.membershipChangeButton.setOnClickListener {

            val intent = Intent(this, MySubscriptionActivity::class.java)
            startActivity(intent)
        }
    }

}