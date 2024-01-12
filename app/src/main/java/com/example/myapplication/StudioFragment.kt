package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.viewmodel.StudioViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

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
            val auslastungTextView = view.findViewById<TextView>(R.id.auslastung)
            auslastungTextView.text = auslastungText

            when {
                randomAuslastung >= 0 && randomAuslastung <= 100 -> {

                    auslastungTextView.setBackgroundColor(Color.GREEN)
                }

                randomAuslastung > 100 && randomAuslastung <= 200 -> {

                    auslastungTextView.setBackgroundColor(Color.YELLOW)
                }

                randomAuslastung > 200 && randomAuslastung <= 300 -> {

                    auslastungTextView.setBackgroundColor(Color.RED)
                }

                else -> {
                    auslastungTextView.setBackgroundColor(Color.TRANSPARENT)
                }
            }
            // Erstelle das BarChart
            val barChart = view.findViewById<BarChart>(R.id.barChart)

            // Erstelle die Datenpunkte für das BarChart
            val entries = ArrayList<BarEntry>()
            entries.add(BarEntry(8f, 30f))
            entries.add(BarEntry(9f, 40f))
            entries.add(BarEntry(10f, 30f))
            entries.add(BarEntry(11f, 40f))
            entries.add(BarEntry(12f, 100f))
            entries.add(BarEntry(13f, 90f))
            entries.add(BarEntry(14f, 50f))
            entries.add(BarEntry(15f, 50f))
            entries.add(BarEntry(16f, 110f))
            entries.add(BarEntry(17f, 200f))
            entries.add(BarEntry(18f, 220f))
            entries.add(BarEntry(19f, 200f))
            entries.add(BarEntry(20f, 150f))
            entries.add(BarEntry(21f, 130f))
            entries.add(BarEntry(22f, 80f))
            entries.add(BarEntry(23f, 50f))
            entries.add(BarEntry(24f, 20f))

            // Data set wird erstellt
            val dataSet = BarDataSet(entries, "durchschnittliche Auslastung")

            // Konfigurieren der Uhrzeitachse
            val xAxis = barChart.xAxis
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return "${value.toInt()}:00"
                }
            }
            //Einstellung der x Achse
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(true)
            xAxis.setDrawLabels(true)
            //jede 2. Uhrzeit wird nur angezeigt
            xAxis.labelCount = entries.size / 2


            // Konfigurieren der Auslastungsachse
            val yAxis = barChart.axisLeft
            //Einstellung der y-Achse
            yAxis.setDrawLabels(false)


            val data = BarData(dataSet)
            barChart.data = data

            // Barchart einstellen
            barChart.setFitBars(true)
            barChart.description.isEnabled = false
            barChart.legend.isEnabled = false

            dataSet.setDrawValues(false)
            //Titel des Barchart
            val title = LegendEntry()
            title.label = "Durchschnittliche Auslastung"
            barChart.legend.setCustom(arrayOf(title))
            barChart.legend.isWordWrapEnabled = true

            barChart.invalidate()



        }
    return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
