package com.logicdojo.daytradealert.service

import com.logicdojo.daytradealert.model.StockTickerRequest
import com.logicdojo.daytradealert.model.StockTickerResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Service for calling Intrinio's API
 */
interface StocksService {
    @POST("/")
    fun getStocks(@Body tickers: StockTickerRequest): Observable<List<StockTickerResponse>>
}