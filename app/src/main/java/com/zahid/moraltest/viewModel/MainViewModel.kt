package com.zahid.moraltest.viewModel

import android.app.Application
import androidx.databinding.ObservableField

import androidx.lifecycle.*
import com.zahid.moraltest.managers.AppManager



class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var _application: Application = application


    var appManager: AppManager = AppManager(application)
    var number = ObservableField<String>()
    var randomText = ObservableField<String>()
    fun updateNumber(){
        appManager.persistenceManager.updateNumber()
    }
    fun getNumber():Int{
        return appManager.persistenceManager.getNumber()
    }
    fun resetCounter(){
        appManager.persistenceManager.resetCounter()
    }
    //Getting Image From Resource using name
    fun getImage(): Int? {
        if (getNumber() <= 9){
            return _application.resources.getIdentifier(
                "number"+getNumber().toString(),
                "drawable",
                _application.packageName
            )
        }
      return null
    }

}