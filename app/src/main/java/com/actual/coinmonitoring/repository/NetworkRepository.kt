package com.actual.coinmonitoring.repository

import com.actual.coinmonitoring.network.Api
import com.actual.coinmonitoring.network.RetrofitInstance

class NetworkRepository {

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()

    suspend fun getInterestCoinPriceData(coin : String) = client.getRecentCoinPrice(coin)


}