package com.logicdojo.daytradealert.ui

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.logicdojo.daytradealert.R
import com.logicdojo.daytradealert.model.ChartDetails
import com.logicdojo.daytradealert.model.StockTickerResponse
import com.logicdojo.daytradealert.model.TrendStatus
import de.codecrafters.tableview.TableDataAdapter

/**
 * Adapter for stock table data
 */
class StockTableDataAdapter(context: Context, data: List<StockTickerResponse>) : TableDataAdapter<StockTickerResponse>(context, data) {

    override fun getCellView(rowIndex: Int, columnIndex: Int, parentView: ViewGroup): View? {
        val chart = getRowData(rowIndex)
        var renderedView: View? = null

        when (columnIndex) {
            0 -> renderedView = renderName(context, convertStockResponseToChartDetails(chart))
            1 -> renderedView = renderTrendStatus(context, convertStockResponseToChartDetails(chart))
            2 -> renderedView = renderPrice(context, convertStockResponseToChartDetails(chart))
            3 -> renderedView = renderDailyChange(context, convertStockResponseToChartDetails(chart))
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
        nameView.maxLines = 1
        symbolView.text = chart.symbol
        nameView.ellipsize = TextUtils.TruncateAt.END
        symbolView.textSize = 10f
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
        view.setTypeface(view.typeface, Typeface.BOLD)
        return view
    }

    private fun renderTrendStatus(context: Context, chart: ChartDetails) : View? {
        val view = TextView(context)
        if (chart.dailyChange > 0) {
            view.setTextColor(ContextCompat.getColor(context, R.color.lime_green))
        } else if (chart.dailyChange < 0) {
            view.setTextColor(ContextCompat.getColor(context, R.color.red))
        } else {
            view.setTextColor(ContextCompat.getColor(context, R.color.white))
        }
        view.ellipsize = TextUtils.TruncateAt.END
        view.text = chart.trendingStatus.toString()
        view.setPadding(16, 4,4,4)
        return view
    }

    private fun renderDailyChange(context: Context, chart: ChartDetails) : View? {
//        val frame = LinearLayout(context)
//        frame.setPadding(4,4,4,4)
//        frame.orientation = LinearLayout.VERTICAL
        val dailyPercent = TextView(context)
//        val dailyValChange = TextView(context)
        if (chart.dailyChange > 0) {
            dailyPercent.setTextColor(ContextCompat.getColor(context, R.color.lime_green))
//            dailyValChange.setTextColor(ContextCompat.getColor(context, R.color.lime_green))
        } else if (chart.dailyChange < 0) {
            dailyPercent.setTextColor(ContextCompat.getColor(context, R.color.red))
//            dailyValChange.setTextColor(ContextCompat.getColor(context, R.color.red))
        } else {
            dailyPercent.setTextColor(ContextCompat.getColor(context, R.color.white))
//            dailyValChange.setTextColor(ContextCompat.getColor(context, R.color.white))
        }
//        frame.gravity = 800005
        dailyPercent.gravity = 1
//        dailyValChange.gravity = 50
//        dailyPercent.setTypeface(dailyPercent.typeface, Typeface.BOLD)
        dailyPercent.ellipsize = TextUtils.TruncateAt.END
//        dailyValChange.ellipsize = TextUtils.TruncateAt.END
//        dailyPercent.maxLines = 1
//        dailyValChange.maxLines = 1
//        dailyValChange.text = (chart.price * (chart.dailyChange / 100)).toString()
        dailyPercent.text = chart.dailyChange.toString() + "%"
//        frame.addView(dailyPercent)
        return dailyPercent
    }

    private fun convertStockResponseToChartDetails(response: StockTickerResponse) : ChartDetails {
        val name = ""
        val dailyChange = 5.0
        return ChartDetails(response.ticker, response.bid_price_4d, name, dailyChange, TrendStatus.RISING)
    }
}