package com.actual.coinmonitoring.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.actual.coinmonitoring.db.entity.InterestCoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterestCoinDAO {

    // getAllData
    @Query("SELECT * FROM interest_coin_table")
    fun getAllData() : Flow<List<InterestCoinEntity>>

    // Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(interestCoinEntity : InterestCoinEntity)


    // update
    // 사용자가 코인 데이터를 선택했다가 다시 취소할 수도 있고, 반대로 선택안된 것을 선택할 수도 있게 함
    @Update
    fun update(interestCoinEntity : InterestCoinEntity)


    // getSelectedCoinList - > 내가 관심있어한 코인 데이터들 가져오는 것
    @Query("SELECT * FROM interest_coin_table WHERE selected = :selected")
    fun getSelectedData(selected : Boolean = true) : List<InterestCoinEntity>


}