package com.actual.coinmonitoring.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.actual.coinmonitoring.db.entity.SelectedCoinPriceEntity
import com.actual.coinmonitoring.network.model.RecentCoinPriceList
import com.actual.coinmonitoring.repository.DBRepository
import com.actual.coinmonitoring.repository.NetworkRepository
import timber.log.Timber
import java.util.Calendar
import java.util.Date

// 최근 거래된 코인 가격 내역을 가져오는 WorkManager

// 1. 관심있어 하는 코인 리스트를 가져와서
// 2. 관심있는 코인 각각의 가격 변동 정보를 가져와서 ( New API )
// 3. 관심있는 코인 각각의 가격 변동 정보를 DB에 저장


class GetCoinPriceRecentContractedWorkManager(val context : Context, workerParameters : WorkerParameters)
    : CoroutineWorker(context, workerParameters) {

    private var dbRepository = DBRepository()
    private var netWorkRepository = NetworkRepository()

    override suspend fun doWork(): Result {

        Timber.d("doWork")

        getAllInterestSelectedCoinData()

        return Result.success()
    }

    // 1. 관심있어 하는 코인 리스트를 가져와서
    suspend fun getAllInterestSelectedCoinData(){

        val selectedCoinList = dbRepository.getallInterestSelectedCoinData()

        val timeStamp = Calendar.getInstance().time

        for(coinData in selectedCoinList){

            Timber.d(coinData.toString())

            // 2. 관심있는 코익 각각의 가격 변동 정보를 가져와서 (New API)
            val recentCoinPriceList = netWorkRepository.getInterestCoinPriceData(coinData.coin_name)

            Timber.d(recentCoinPriceList.toString())

            saveSelectedCoinPrice(
                coinData.coin_name,
                recentCoinPriceList,
                timeStamp
            )
        }

    }

    // 3. 관심있는 코인 각각의 가격 변동 정보를 DB에 저장

    fun saveSelectedCoinPrice(
        coinName : String,
        recentCoinPriceList : RecentCoinPriceList,
        timestamp : Date
    ){

        val selectedCoinPriceEntity = SelectedCoinPriceEntity(
            0,
            coinName,
            recentCoinPriceList.data[0].transaction_date,
            recentCoinPriceList.data[0].type,
            recentCoinPriceList.data[0].units_traded,
            recentCoinPriceList.data[0].price,
            recentCoinPriceList.data[0].total,
            timestamp

            )

        dbRepository.insertCoinPriceData(selectedCoinPriceEntity)

    }


}