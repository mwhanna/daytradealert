package com.logicdojo.daytradealert.model

import java.math.BigInteger

/**
 * Request object for Intrinio stocks API
 */
class StockTickerRequest(val tickers: String)

/**
 * Response object for Intrinio stocks API
 */
class StockTickerResponse(
        val quote_time: BigInteger,
        val ticker: String,
        val total_volume: Int,
        val ask_price_4d: Double,
        val bid_price_4d: Double,
        val bid_size: Int,
        val ask_size: Int
)