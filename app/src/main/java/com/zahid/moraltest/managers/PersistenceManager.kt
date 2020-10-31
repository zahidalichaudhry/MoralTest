package com.zahid.moraltest.managers

import android.content.Context
import android.content.SharedPreferences


class PersistenceManager(var context: Context) {
    var preference: SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    val number = "Number"
    fun updateNumber(){
        var saveNumber = preference.getInt(number,-1);
        saveNumber++
        preference.edit().putInt(number,saveNumber).apply()

    }
    fun getNumber():Int{
        return  preference.getInt(number,0);
    }
    fun resetCounter(){
        preference.edit().putInt(number,0).apply()
    }
}
