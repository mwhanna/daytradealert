package com.logicdojo.daytradealert.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.logicdojo.daytradealert.R
import com.logicdojo.daytradealert.model.ChartDetails
import de.codecrafters.tableview.TableDataAdapter

/**
 * Adapter for stock table data
 */
class StockTableDataAdapter(context: Context, data: List<ChartDetails>) : TableDataAdapter<ChartDetails>(context, data) {

    override fun getCellView(rowIndex: Int, columnIndex: Int, parentView: ViewGroup): View? {
        val chart = getRowData(rowIndex)
        var renderedView: View? = null

        when (columnIndex) {
            0 -> renderedView = renderName(context, chart)
            1 -> renderedView = renderColorNetChange(context, chart, true)
            2 -> renderedView = renderPrice(context, chart)
            3 -> renderedView = renderColorNetChange(context, chart, false)
        }

        return renderedView
    }

    private fun renderName(context: Context, chart: ChartDetails) : View? {
        val frame = LinearLayout(context)
        frame.setPadding(4,4,4,4)
        frame.orientation = LinearLayout.VERTICAL
        val nameView = TextView(context)
        val symbolView = TextView(context)
        nameView.setPadding(4,4,4,0)
        symbolView.setPadding(4,0,4,4)
        nameView.setTextColor(ContextCompat.getColor(context, R.color.white))
        symbolView.setTextColor(ContextCompat.getColor(context, R.color.white))
        nameView.text = chart.name
        symbolView.text = chart.symbol
        nameView.gravity = 30
        symbolView.gravity = 50
        frame.addView(nameView)
        frame.addView(symbolView)
        return frame
    }

    private fun renderPrice(context: Context, chart: ChartDetails) : View? {
        val view = TextView(context)
        view.setPadding(16,4,4,4)
        view.setTextColor(ContextCompat.getColor(context, R.color.white))
        view.text = chart.price.toString()
        return view
    }

    private fun renderColorNetChange(context: Context, chart: ChartDetails, isTrend: Boolean) : View? {
        val view = TextView(context)
        if (chart.dailyChange > 0) {
            view.setTextColor(ContextCompat.getColor(context, R.color.lime_green))
        } else if (chart.dailyChange < 0) {
            view.setTextColor(ContextCompat.getColor(context, R.color.red))
        } else {
            view.setTextColor(ContextCompat.getColor(context, R.color.white))
        }
        if (!isTrend) view.gravity = 1 else view.setPadding(16, 4,4,4)
        view.text = if (isTrend) chart.trendingStatus.toString() else chart.dailyChange.toString() + "%"
        return view
    }
}