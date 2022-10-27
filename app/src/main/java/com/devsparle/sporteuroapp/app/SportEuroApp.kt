package com.devsparle.sporteuroapp.app

import android.app.Application
import com.devsparle.sporteuroapp.utils.TimberConfiguration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SportEuroApp : Application() {


    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber(){
        TimberConfiguration.configure()
    }

}