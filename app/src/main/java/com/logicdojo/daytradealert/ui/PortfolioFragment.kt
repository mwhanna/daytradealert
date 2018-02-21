package com.logicdojo.daytradealert.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.logicdojo.daytradealert.R
import com.logicdojo.daytradealert.model.TrendStatus
import de.codecrafters.tableview.SortableTableView
import de.codecrafters.tableview.model.TableColumnWeightModel

/**
 * Fragment for my portfolio
 **/
class PortfolioFragment : Fragment() {
    private var mDataAdapter: StockTableDataAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_portfolio, container, false)

        if (view is SortableTableView<*>) {
            mDataAdapter = StockTableDataAdapter(view.context, emptyList())
            view.headerAdapter = StockTableHeaderAdapter(view.context, 4)
//            view.dataAdapter = mDataAdapter
//            view.dataAdapter = StockTableDataAdapter(view.context, listOfCharts)
            val columnModel = TableColumnWeightModel(4)
            columnModel.setColumnWeight(0, 3)
            columnModel.setColumnWeight(1, 2)
            columnModel.setColumnWeight(2, 2)
            columnModel.setColumnWeight(3, 2)
            columnModel.setColumnWeight(4, 1)
//            (tableView as SortableTableView<Any>).setColumnComparator(0, NameComparator())
            view.columnModel = columnModel
        }

        return view
    }

    private fun getTrendStatus(dailyChange: Double): TrendStatus {
        if (dailyChange > 0.0) {
            return TrendStatus.RISING
        } else if (dailyChange < 0.0) {
            return TrendStatus.FALLING
        } else {
            return TrendStatus.FLAT
        }
//        return when (dailyChange) {
//            0.0 -> TrendStatus.FLAT
//            in 0.1..1.5 -> TrendStatus.RISING_SLOWLY
//            in 1.51..3.5 -> TrendStatus.RISING
//            in 3.51..5.5 -> TrendStatus.RISING_FAST
//            in 5.51..200.0 -> TrendStatus.EXPLODING
//            in -0.1..-1.5 -> TrendStatus.FALLING_SLOWLY
//            in -1.51..-3.5 -> TrendStatus.FALLING
//            in -3.51..-5.5 -> TrendStatus.FALLING_FAST
//            in -5.51..-200.0 -> TrendStatus.PLUMMETING
//            else -> TrendStatus.FLAT
//        }
    }
}