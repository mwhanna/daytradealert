package com.logicdojo.daytradealert.model

/**
 * Created by matt_hanna on 2018-02-08.
 */
class ChartDetails(val symbol: String,
                   val price: Double,
                   val name: String,
                   val dailyChange: Double,
                   val trendingUp: Boolean = false,
                   val netChange: Double? = null,
                   val volume: Double? = null,
                   val rsi: Double? = null,
                   val ema: Double? = null,
                   val trendLevel: TrendLevel? = null,
                   val fiveMinAverage: Double? = null,
                   val changeSinceLastFlat: Double? = null,
                   val fiveMinChangeHistory: Array<Double>? = null)


enum class TrendLevel(val level: Int) {
    SMALL(1),
    MEDIUM(2),
    HIGH(3)
}