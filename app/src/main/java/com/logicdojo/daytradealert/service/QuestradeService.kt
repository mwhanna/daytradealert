package com.logicdojo.daytradealert.service

import com.logicdojo.daytradealert.model.BatchStockResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Service for calling Questrade's API
 */
interface QuestradeService {
    @GET("/stock/market/batch?symbols=&types=quote,news,chart&range=1m&last=5")
    fun getStocks(): Call<BatchStockResponse>
}