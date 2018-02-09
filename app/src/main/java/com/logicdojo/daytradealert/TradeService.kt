package com.logicdojo.daytradealert

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by matt_hanna on 2018-01-31.
 */
interface TradeService {
    @GET("/stock/market/batch?symbols=&types=quote,news,chart&range=1m&last=5")
    fun getStocks(): Call<BatchStockResponse>
}