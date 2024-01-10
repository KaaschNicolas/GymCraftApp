package com.example.myapplication.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.CourseListFragment

class TabAdapter(
    private val activity: AppCompatActivity
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0 -> CourseListFragment()
            1 -> CourseListFragment()
            2 -> StudioFragment()
            3 -> ProfileFragment()
            else -> throw RuntimeException("Invalid position: $position")
        }
    }
}