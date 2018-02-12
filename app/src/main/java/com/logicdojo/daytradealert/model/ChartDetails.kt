package com.logicdojo.daytradealert.model

/**
 * Chart details object
 **/
class ChartDetails(val symbol: String,
                   val price: Double,
                   val name: String,
                   val dailyChange: Double,
                   val trendingStatus: TrendStatus,
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

enum class TrendStatus(level: String) {
    RISING_SLOWLY("RISING SLOWLY"),
    RISING("RISING"),
    RISING_FAST("RISING FAST"),
    EXPLODING("EXPLODING"),
    FALLING_SLOWLY("FALLING SLOWLY"),
    FALLING("FALLING SLOWLY"),
    FALLING_FAST("FALLING FAST"),
    PLUMMETING("PLUMMETING"),
    TURNING_AROUND("TURNING AROUND"),
    TURNING_DOWNWARD("TURNING DOWNWARD"),
    SLOWING_DOWN("SLOWING DOWN"),
    FLAT("FLAT")
}