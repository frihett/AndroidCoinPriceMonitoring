package com.actual.coinmonitoring.network.model

import com.actual.coinmonitoring.dataModel.RecentPriceData

data class RecentCoinPriceList (

    val status : String,
    val data : List<RecentPriceData>

)