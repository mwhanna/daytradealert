package com.logicdojo.daytradealert.model

import java.math.BigInteger

/**
 * Coin details object
 **/
class CoinDetails(
        val symbol: String,
        val cap: String,
        val change: CoinChangeDetails,
        val price: String,
        val coinheat: Int,
        val url: String
)

class CoinDetails2(
        val symbol_id: String? = null,
        val time_exchange: String? = null,
        val time_coinapi: String? = null,
        val ask_price: Double? = null,
        val ask_size: Double? = null,
        val bid_price: Double? = null,
        val bid_size: Double? = null,
        val last_trade: SampleTrade? = null
)

class CoinDetails3(
        val symbol : String,
        val amountAssetID : String,
        val amountAssetName : String,
        val amountAssetDecimals : String,
        val amountAssetTotalSupply :String,
        val amountAssetMaxSupply : String,
        val amountAssetCirculatingSupply : String,
        val priceAssetID : String,
        val priceAssetName : String,
        val priceAssetDecimals : Int,
        val priceAssetTotalSupply : String,
        val priceAssetMaxSupply : String,
        val priceAssetCirculatingSupply : String,
        val day_open : String? = null,
        val day_high : String? = null,
        val day_low : String? = null,
        val day_close : String? = null,
        val day_vwap : String? = null,
        val day_volume : String? = null,
        val day_priceVolume : String? = null,
        val totalTrades : Int,
        val firstTradeDay : BigInteger,
        val lastTradeDay : BigInteger
)

class CoinDetails4(
        val symbol_id: String? = null,
        val time_exchange: String? = null,
        val time_coinapi: String? = null,
        val ask_price: Double? = null,
        val ask_size: Double? = null,
        val bid_price: Double? = null,
        val bid_size: Double? = null,
        val last_trade: SampleTrade? = null
)

class SampleTrade(
        val time_exchange: String,
        val time_coinapi: String,
        val uuid: String,
        val price: Double,
        val size: Double,
        val taker_side: String
)

class CoinChangeDetails(val hour: String, val day: String)