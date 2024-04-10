package com.example.lets_plot_skiko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.statistics.kandy.layers.candlestick
import org.jetbrains.kotlinx.kandy.util.context.*
import org.jetbrains.letsPlot.skia.compose.PlotPanel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val date = List(10) { LocalDate(2024, 4, it + 1) }
        val open = listOf(10.0, 15.0, 12.0, 18.0, 14.0, 16.0, 20.0, 22.0, 19.0, 25.0)
        val high = listOf(18.0, 17.0, 20.0, 22.0, 18.0, 22.0, 25.0, 24.0, 27.0, 28.0)
        val low = listOf(8.0, 10.0, 9.0, 11.0, 12.0, 15.0, 18.0, 17.0, 18.0, 22.0)
        val close = listOf(15.0, 12.0, 18.0, 14.0, 16.0, 20.0, 22.0, 19.0, 25.0, 23.0)


        val figure =  plot {
            candlestick(date, open, high, low, close) {
                increase {
                    fillColor = Color.hex("#00fefe")
                    alpha = 0.9
                }
                decrease {
                    fillColor = Color.hex("#ea2211")
                    alpha = 0.5
                }
                borderLine.color = Color.GREY
                width = 0.7
            }
            y.axis.name = "Price, €"
            x.axis.name = "Date"
        }.toLetsPlot()

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxSize().padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
                ) {

                    PlotPanel(
                        figure = figure,
                        modifier = Modifier.fillMaxSize()
                    ) { computationMessages ->
                        computationMessages.forEach { println("[DEMO APP MESSAGE] $it") }
                    }
                }
            }
        }
    }
}
