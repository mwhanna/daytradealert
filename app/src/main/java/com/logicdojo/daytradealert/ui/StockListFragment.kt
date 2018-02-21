package com.logicdojo.daytradealert.ui

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.logicdojo.daytradealert.R
import com.logicdojo.daytradealert.model.*
import com.logicdojo.daytradealert.service.StocksService
import com.logicdojo.daytradealert.service.CryptoService
import retrofit2.Retrofit
import de.codecrafters.tableview.SortableTableView
import de.codecrafters.tableview.TableView
import de.codecrafters.tableview.model.TableColumnWeightModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_stock_list.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Fragment for stocks list
**/
class StockListFragment : Fragment() {
    private var canadaMJStocksDisposable: Disposable? = null
    private var mDataAdapter: StockTableDataAdapter? = null
    private var mExchangeName: String = "TSX"

    val stocksService by lazy {
        StockListFragment.createStocksService()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mExchangeName = arguments.getString(ARG_STOCK_EXCHANGE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_stock_list, container, false)

        val listOfCharts = ArrayList<ChartDetails>()
        listOfCharts.add(ChartDetails("CVE:ABCN", 2.46, "ABCann Medical", -5.02, getTrendStatus(-5.02)))
        listOfCharts.add(ChartDetails("TSE:ACB", 10.93, "Aurora Cannabis", -1.89, getTrendStatus(-1.89)))
        listOfCharts.add(ChartDetails("TSE:APH", 14.97, "Aphria Inc.", -3.48, getTrendStatus(-3.48)))
        listOfCharts.add(ChartDetails("CNSX:BE", 2.39, "Beleave Inc.", -2.45, getTrendStatus(-2.45)))
        listOfCharts.add(ChartDetails("TSE:WEED", 27.72, "Canopy Growth Corp.", 2.17, getTrendStatus(2.17)))
        listOfCharts.add(ChartDetails("TSE:CMED", 35.18, "CanniMed Therapeutics", -0.79, getTrendStatus(-0.79)))
        listOfCharts.add(ChartDetails("CVE:MJN", 9.70, "Cronos Group Inc.", -3.50, getTrendStatus(-3.50)))
        listOfCharts.add(ChartDetails("CVE:EMC", 1.58, "Emblem Corp", -1.13, getTrendStatus(-1.24)))
        listOfCharts.add(ChartDetails("CVE:EMH", 5.99, "Emerald Health", -3.50, getTrendStatus(-3.50)))
        listOfCharts.add(ChartDetails("CVE:HIP", 1.04, "Newstrike Resources", -1.24, getTrendStatus(-1.24)))
        listOfCharts.add(ChartDetails("CVE:HVT", 1.13, "Harvest One", -4.32, getTrendStatus(-4.32)))
        listOfCharts.add(ChartDetails("CVE:IMH", 1.90, "Invictus MD", -3.70, getTrendStatus(-3.70)))
        listOfCharts.add(ChartDetails("TSE:LEAF", 21.76, "MedReleaf Corp", -4.27, getTrendStatus(-4.27)))
        listOfCharts.add(ChartDetails("CNSX:MARI", 3.21, "Maricann Group", -4.13, getTrendStatus(-4.13)))
        listOfCharts.add(ChartDetails("CVE:OGI", 4.19, "Organigram Holdings", -2.06, getTrendStatus(-2.06)))
        listOfCharts.add(ChartDetails("CVE:FIRE", 2.05, "Supreme Cannabis", 4.27, getTrendStatus(4.27)))
        listOfCharts.add(ChartDetails("CNSX:TER", 3.16, "Terrascend Corp", -4.17, getTrendStatus(-4.17)))
        listOfCharts.add(ChartDetails("CNSX:THC", 1.43, "THC Biomed Intl", -2.10, getTrendStatus(-2.10)))
        listOfCharts.add(ChartDetails("CVE:THCX", 4.23, "Hydropothecary Corp", 2.60, getTrendStatus(2.60)))
        listOfCharts.add(ChartDetails("CNSX:TRST", 9.18, "CannTrust Holdings", 3.03, getTrendStatus(3.03)))
        listOfCharts.add(ChartDetails("CNSX:HIKU", 3.04, "Hiku Brands Company", 1.91, getTrendStatus(1.91)))
        listOfCharts.add(ChartDetails("CVE:NINE", 2.14, "Delta 9 Cannabis", 1.90, getTrendStatus(1.90)))
        listOfCharts.add(ChartDetails("CVE:WMD", 2.26, "WeedMD Inc.", 3.59, getTrendStatus(3.59)))

        if (view is SortableTableView<*>) {
            setupStocksSubscription(view, listOfCharts)
            mDataAdapter = StockTableDataAdapter(view.context, emptyList())
            view.headerAdapter = StockTableHeaderAdapter(view.context, 4)
//            view.dataAdapter = mDataAdapter
//            view.dataAdapter = StockTableDataAdapter(view.context, listOfCharts)
            val columnModel = TableColumnWeightModel(4)
            columnModel.setColumnWeight(0, 3)
            columnModel.setColumnWeight(1, 2)
            columnModel.setColumnWeight(2, 2)
            columnModel.setColumnWeight(3,2)
            columnModel.setColumnWeight(4,1)
//            (tableView as SortableTableView<Any>).setColumnComparator(0, NameComparator())
            view.columnModel = columnModel
        }

        return view
    }

    private fun setupStocksSubscription(view: View, tickers: List<ChartDetails>) {

        var tickerString = ""
        val exchange = if (mExchangeName == "NASDAQ") ".NB," else ".TSX,"
        for (ticker in tickers) tickerString += ticker.symbol + exchange
        canadaMJStocksDisposable = stocksService.getStocks(StockTickerRequest(tickerString.substring(tickerString.length -1)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    val adapter = StockTableDataAdapter(view.context, response)
                    tableView.dataAdapter = adapter
                }
    }

    private fun getTrendStatus(dailyChange: Double) : TrendStatus {
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

    override fun onDestroy() {
        super.onDestroy()
        canadaMJStocksDisposable?.dispose()
    }

    companion object {

        private val ARG_STOCK_EXCHANGE = "stock-exchange"

        fun newInstance(exchangeType: ExchangeType): StockListFragment {
            val fragment = StockListFragment()
            val args = Bundle()
            args.putString(ARG_STOCK_EXCHANGE, exchangeType.toString())
            fragment.arguments = args
            return fragment
        }

        fun createStocksService(): StocksService {
            val token = ""
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://www5.quodd.com/quoddsnap/c/intrinio/t/" + token)
                    .client(httpClient.build())
                    .build()

            return retrofit.create(StocksService::class.java)
        }
    }
}