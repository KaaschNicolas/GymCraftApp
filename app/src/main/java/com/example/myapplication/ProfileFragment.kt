package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.models.Course
import com.example.myapplication.models.Customer
import com.example.myapplication.viewmodel.CourseViewModel
import com.example.myapplication.viewmodel.LoginViewModel
import com.example.myapplication.viewmodels.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var viewModel: CustomerViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        val customer: Customer? = viewModel?.getUser()

        Log.i("User", "${customer?.firstName}")

        binding.mitgliedschaftButton.setOnClickListener{
            val intent = Intent(requireContext(), MembershipActivity::class.java)
            startActivity(intent)
        }
        //binding.memberSince.text = "Mitglied seit: " +getCurrentCustomer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
