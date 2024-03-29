package com.actual.coinmonitoring.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.actual.coinmonitoring.background.GetCoinPriceRecentContractedWorkManager
import com.actual.coinmonitoring.view.main.MainActivity
import com.actual.coinmonitoring.databinding.ActivitySelectBinding
import com.actual.coinmonitoring.view.adapter.SelectRVAdapter
import timber.log.Timber
import java.util.concurrent.TimeUnit

// 빗썸코인 싸이트
//https://apidocs.bithumb.com/reference/%ED%98%84%EC%9E%AC%EA%B0%80-%EC%A0%95%EB%B3%B4-%EC%A1%B0%ED%9A%8C-all

class SelectActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelectBinding

    private val viewModel : SelectViewModel by viewModels()

    private lateinit var selectRVAdapter : SelectRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCurrentCoinList()
        viewModel.currentPriceResult.observe(this, Observer {

            selectRVAdapter = SelectRVAdapter(this, it)

            binding.coinListRV.adapter = selectRVAdapter
            binding.coinListRV.layoutManager = LinearLayoutManager(this)

            Timber.d(it.toString())
        })



        binding.laterTextArea.setOnClickListener {

            viewModel.setUpFirstFlag()
            viewModel.saveSelectedCoinList(selectRVAdapter.selectedCoinList)


        }

        viewModel.save.observe(this, Observer {

            if (it.equals("done")) {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // 가장 처음으로 우리가 저장한 코인 정보가 저장되는 시점

                saveInterestCoinDataPeriodic()
            }

        })
    }
        private fun saveInterestCoinDataPeriodic(){

            val myWork = PeriodicWorkRequest.Builder(
                GetCoinPriceRecentContractedWorkManager::class.java,
                15,
                TimeUnit.MINUTES
            ).build()

            WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "GetCoinPriceRecentContractedWorkManager",
                ExistingPeriodicWorkPolicy.KEEP,
                myWork
            )

        }


}