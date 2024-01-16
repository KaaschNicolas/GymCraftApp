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

@AndroidEntryPoint
//Klasse repräsentiert die Studio Übersicht
class StudioFragment(
) : Fragment(R.layout.fragment_studio) {
    private var viewModel: StudioViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(StudioViewModel::class.java)
        var view: View = inflater.inflate(R.layout.fragment_studio, container, false)

        // Studio Daten abrufen
        val studio = viewModel?.getStudio(id = 1)

        // Informationen des Studios in die TextViews setzen
        studio?.let {
            view.findViewById<TextView>(R.id.studioName).text = it.studioName
            view.findViewById<TextView>(R.id.openingHours).text = it.openingHours
            view.findViewById<TextView>(R.id.description).text = it.description

            // Zufallszahl für die Auslastung generieren
            val randomAuslastung = Random.nextInt(0, 301)
            //Text der aktuellen Auslastung definieren
            val auslastungText = "$randomAuslastung/300"
            //Auslastung in die Text View setzen
            view.findViewById<TextView>(R.id.auslastung).text = auslastungText
            val auslastungTextView = view.findViewById<TextView>(R.id.auslastung)
            auslastungTextView.text = auslastungText

            // Farbe je nach aktueller Auslastung ändern
            when {
                randomAuslastung in 0..100 -> {
                    auslastungTextView.setBackgroundResource(R.drawable.oval_shape_green)
                }
                randomAuslastung in 101..200 -> {
                    auslastungTextView.setBackgroundResource(R.drawable.oval_shape_yellow)
                }
                randomAuslastung in 201..300 -> {
                    auslastungTextView.setBackgroundResource(R.drawable.oval_shape_red)
                }
                else -> {
                    auslastungTextView.setBackgroundResource(R.drawable.round_box)
                }
            }
            // Erstellen des BarCharts
            val barChart = view.findViewById<BarChart>(R.id.barChart)

            // Erstellen der Datenpunkte für das BarChart
            val entries = ArrayList<BarEntry>()
            entries.add(BarEntry(10f, 30f))
            entries.add(BarEntry(11f, 40f))
            entries.add(BarEntry(12f, 100f))
            entries.add(BarEntry(13f, 90f))
            entries.add(BarEntry(14f, 50f))
            entries.add(BarEntry(15f, 50f))
            entries.add(BarEntry(16f, 110f))
            entries.add(BarEntry(17f, 200f))
            entries.add(BarEntry(18f, 250f))
            entries.add(BarEntry(19f, 200f))
            entries.add(BarEntry(20f, 150f))
            entries.add(BarEntry(21f, 130f))

            // Data set wird erstellt
            val dataSet = BarDataSet(entries, "durchschnittliche Auslastung")
            dataSet.color = Color.rgb(74, 112, 139)

            // Konfigurieren der x-Achsenwerte
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

            //Nur jede 2. Uhrzeit wird nur angezeigt
            xAxis.labelCount = entries.size / 2
            //Weiße Textfarbe
            xAxis.axisLineColor = Color.WHITE
            xAxis.textColor = Color.WHITE


            // Konfiguration der y-Achse auf der rechten Seite
            val yAxis = barChart.axisRight

            // Maximale Wert der y-Achse auf 300 setzen
            yAxis.axisMaximum = 300f

            // Minimale Wert der y-Achse auf 0 setzen
            yAxis.axisMinimum = 0f

            // Anzeige der y-Achsenwerte konfigurieren
            yAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return when (value) {
                        0f -> "0%"
                        300f -> "100%"
                        else -> ""
                    }
                }
            }

            //Einstellung der y-Achse
            yAxis.setDrawLabels(true)
            yAxis.axisLineColor = Color.WHITE
            yAxis.textColor = Color.WHITE

            // Daten setzen
            val data = BarData(dataSet)
            barChart.data = data


            // Barchart anpassen
            barChart.setFitBars(true)
            barChart.description.isEnabled = false
            barChart.legend.isEnabled = false

            // Anzeige der Werte über den Balken ausschalten
            dataSet.setDrawValues(false)
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
