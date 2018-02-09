package com.logicdojo.daytradealert

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.logicdojo.daytradealert.StockListFragment.OnListFragmentInteractionListener

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class StockListRecyclerViewAdapter(
        val mCharts: List<ChartDetails>,
        val mListener: OnListFragmentInteractionListener?):
        RecyclerView.Adapter<StockListRecyclerViewAdapter.StockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_stocklist, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.mSymbolView?.text = mCharts[position].symbol
        holder.mPriceView?.text = mCharts[position].price.toString()
        holder.mDailyChangeView?.text = mCharts[position].dailyChange.toString()

        holder.mView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return mCharts.size
    }

    inner class StockViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mSymbolView: TextView? = null
        var mPriceView: TextView? = null
        var mDailyChangeView: TextView? = null

        init {
            mSymbolView = mView.findViewById(R.id.symbol)
            mDailyChangeView = mView.findViewById(R.id.dailyChange)
            mPriceView = mView.findViewById(R.id.price)
        }
    }
}
