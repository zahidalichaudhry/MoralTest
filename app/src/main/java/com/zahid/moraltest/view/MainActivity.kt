package com.zahid.moraltest.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.zahid.moraltest.R
import com.zahid.moraltest.databinding.ActivityMainBinding
import com.zahid.moraltest.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Declare the necessary variables
    lateinit var mReceiver: BroadcastReceiver


    var filter = IntentFilter(Intent.ACTION_SCREEN_ON)

    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        initUI()
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_USER_PRESENT)
        btn_reset.setOnClickListener {
            mainViewModel.resetCounter()
            updateUI()
        }
        registerLockScreenBroadCast()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    fun initUI() {
        binding.number = mainViewModel.number
        binding.randomText = mainViewModel.randomText
    }

    fun updateUI() {

        mainViewModel.number.set("Number:" + mainViewModel.getNumber().toString())
        mainViewModel.randomText.set("Random text on number ${mainViewModel.getNumber()}")
        Glide.with(this).load(mainViewModel.getImage())
            .into(img)
    }

    fun registerLockScreenBroadCast() {
        mReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action.equals(Intent.ACTION_SCREEN_OFF)) {
                } else if (intent.action.equals(Intent.ACTION_SCREEN_ON)) {
                    mainViewModel.updateNumber()
                    updateUI()
                } else if (intent.action.equals(Intent.ACTION_USER_PRESENT)) {
                }

            }
        }
        registerReceiver(mReceiver, filter)
    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        super.onDestroy()
    }
}