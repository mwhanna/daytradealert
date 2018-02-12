package com.logicdojo.daytradealert.ui

import com.logicdojo.daytradealert.model.TrendStatus

/**
 * Comparator for TrendStatus
 */
class TrendStatusComparator : Comparator<TrendStatus> {
    override fun compare(status1: TrendStatus, status2: TrendStatus): Int {
        return status1.toString().compareTo(status2.toString())
    }
}