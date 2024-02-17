package com.actual.coinmonitoring.repository

import com.actual.coinmonitoring.App
import com.actual.coinmonitoring.db.CoinPriceDatabase
import com.actual.coinmonitoring.db.entity.InterestCoinEntity
import com.actual.coinmonitoring.db.entity.SelectedCoinPriceEntity

class DBRepository {

    val context = App.context()
    val db = CoinPriceDatabase.getDatabase(context)

    // InterestCoin

    // 전체 코인 데이터 가져오기
    fun getAllInterestCoinData() = db.interestCoinDAO().getAllData()

    // 코인 데이터 넣고
    fun insertInterestCoinData(interestCoinEntity : InterestCoinEntity) = db.interestCoinDAO().insert(interestCoinEntity)

    // 코인 데이터 업데이트
    fun updateInterestCoinData(interestCoinEntity : InterestCoinEntity) = db.interestCoinDAO().update(interestCoinEntity)

    // 사용자가 관심있어한 코인만 가져오기
    fun getallInterestSelectedCoinData() = db.interestCoinDAO().getSelectedData()

    // CoinPrice
    fun getAllCoinPriceData() = db.selectedCoinDAO().getAllData()

    fun insertCoinPriceData(selectedCoinPriceEntity: SelectedCoinPriceEntity) = db.selectedCoinDAO().insert(selectedCoinPriceEntity)

    fun getOneSelectedCoinData(coinName : String) = db.selectedCoinDAO().getOneCoinData(coinName)

}