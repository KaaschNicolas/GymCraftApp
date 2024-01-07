package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.viewmodel.StudioViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class StudioFragment(
) : Fragment(R.layout.fragment_studio) {
    private var viewModel: StudioViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(StudioViewModel::class.java)
        var view: View = inflater.inflate(R.layout.fragment_studio, container, false)

        val studio = viewModel?.getStudio(id = 1)

        studio?.let {
            view.findViewById<TextView>(R.id.studioName).text = it.studioName
            view.findViewById<TextView>(R.id.openingHours).text = it.openingHours
            view.findViewById<TextView>(R.id.description).text = it.description

        val randomAuslastung = Random.nextInt(0, 301)
        val auslastungText = "$randomAuslastung/300"
        view.findViewById<TextView>(R.id.auslastung).text = auslastungText
    }
    return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
