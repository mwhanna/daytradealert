package com.logicdojo.daytradealert.model

/**
 * Comparator for TrendStatus
 */
class TrendStatusComparator : Comparator<TrendStatus> {
    override fun compare(status1: TrendStatus, status2: TrendStatus): Int {
        return status1.toString().compareTo(status2.toString())
    }
}

/**
 * Comparator for Name
 */
class NameComparator : Comparator<Any> {
    override fun compare(name1: Any, name2: Any): Int {
        return name1.toString().compareTo(name2.toString())
    }
}

/**
 * Comparator for Price
 */
class PriceComparator : Comparator<Double> {
    override fun compare(price1: Double, price2: Double): Int {
        return price1.compareTo(price2)
    }
}