package com.logicdojo.daytradealert.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.logicdojo.daytradealert.R
import com.logicdojo.daytradealert.service.QuestradeService
import com.logicdojo.daytradealert.model.ChartDetails
import com.logicdojo.daytradealert.model.TrendStatus
import de.codecrafters.tableview.TableView
import retrofit2.Retrofit

/**
 * Fragment for stocks list
**/
class StockListFragment : Fragment() {
    private var mColumnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_stocklist_list, container, false)

        val listOfCharts = ArrayList<ChartDetails>()
        listOfCharts.add(ChartDetails("AAPL", 162.80, "Apple Inc.", 5.25, TrendStatus.RISING))
        listOfCharts.add(ChartDetails("MARI", 162.80, "Maricann", 5.25, TrendStatus.RISING))
        listOfCharts.add(ChartDetails("MPX", 162.80, "Apple Inc.", 5.25, TrendStatus.RISING))
        listOfCharts.add(ChartDetails("HVT", 162.80, "Apple Inc.", 5.25, TrendStatus.RISING))
        listOfCharts.add(ChartDetails("TNY", 162.80, "Apple Inc.", 5.25, TrendStatus.RISING))
        listOfCharts.add(ChartDetails("XIV", 0.380, "Apple Inc.", 5.25, TrendStatus.RISING))
        listOfCharts.add(ChartDetails("AAPL", 162.80, "Apple Inc.", 5.25, TrendStatus.RISING))
        listOfCharts.add(ChartDetails("MARI", 162.80, "Maricann", 5.25, TrendStatus.RISING))
        listOfCharts.add(ChartDetails("MPX", 162.80, "Apple Inc.", 5.25, TrendStatus.RISING))
        listOfCharts.add(ChartDetails("HVT", 162.80, "Apple Inc.", 5.25, TrendStatus.RISING))
        listOfCharts.add(ChartDetails("TNY", 162.80, "Apple Inc.", 5.25, TrendStatus.RISING))
        listOfCharts.add(ChartDetails("XIV", 0.380, "Apple Inc.", 5.25, TrendStatus.RISING))

        if (view is TableView<*>) {
            view.headerAdapter = StockTableHeaderAdapter(view.context, 4)
            view.dataAdapter = StockTableDataAdapter(view.context, listOfCharts)
        }

        return view
    }

    fun getStocks() : List<ChartDetails> {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build()

        val service = retrofit.create<QuestradeService>(QuestradeService::class.java)
        return emptyList()
    }

    companion object {

        private val ARG_COLUMN_COUNT = "column-count"
        private val TABLE_HEADERS : Array<String> = arrayOf("Name (A-Z)", "Trend", "Price", "Change")

        fun newInstance(columnCount: Int): StockListFragment {
            val fragment = StockListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}