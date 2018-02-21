package com.logicdojo.daytradealert.ui

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.logicdojo.daytradealert.R
import com.logicdojo.daytradealert.model.CoinDetails
import com.logicdojo.daytradealert.model.TrendStatus
import com.logicdojo.daytradealert.service.CryptoService
import de.codecrafters.tableview.SortableTableView
import de.codecrafters.tableview.model.TableColumnWeightModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Fragment for crypto list
 **/
class CryptoListFragment : Fragment() {
    private var top20CryptoDisposable: Disposable? = null
    private var mDataAdapter: CryptoTableDataAdapter? = null

    val cryptoService by lazy {
        CryptoListFragment.createCryptoService()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_stock_list, container, false)

        if (view is SortableTableView<*>) {
            setupCryptosSubscription(view)
            mDataAdapter = CryptoTableDataAdapter(view.context, emptyList())
            view.headerAdapter = CryptoTableHeaderAdapter(view.context, 4)
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

    private fun setupCryptosSubscription(view: View) {
        val list = ArrayList<CoinDetails>()
        top20CryptoDisposable = cryptoService.getCurrentCryptos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    Log.v("RESPONSE -----    ", response.toString())
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
        top20CryptoDisposable?.dispose()
    }

    companion object {
        fun createCryptoService(): CryptoService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("http://marketdata.wavesplatform.com/api/")
                    .client(httpClient.build())
                    .build()

            return retrofit.create(CryptoService::class.java)
        }
    }
}