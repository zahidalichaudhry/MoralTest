package com.zahid.moraltest.view

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
    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        initUI();
        btn_reset.setOnClickListener{
            mainViewModel.resetCounter()
            updateUI()
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.updateNumber()
        updateUI();
    }
    fun initUI(){
        binding.number = mainViewModel.number
        binding.randomText = mainViewModel.randomText
    }
    fun updateUI(){

        mainViewModel.number.set("Number:"+ mainViewModel.getNumber().toString())
        mainViewModel.randomText.set("Random text on number ${mainViewModel.getNumber()}")
        Glide.with(this).load(mainViewModel.getImage())
            .into(img)
    }
}