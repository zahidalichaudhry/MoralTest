package com.zahid.moraltest.managers

import android.app.Application
import android.content.Context
import android.util.Log

class AppManager(application: Application) {
    val context: Context
    var persistenceManager: PersistenceManager

    init {
        context = application.applicationContext
        persistenceManager = PersistenceManager(context)
        Log.e("AppManager", "in")
    }
}