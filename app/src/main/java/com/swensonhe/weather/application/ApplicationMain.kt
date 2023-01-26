package com.swensonhe.weather.application

import android.app.Application

import com.swensonhe.weather.util.general.Utils.getAppVersion
import com.swensonhe.weather.util.network.NetworkMonitoringUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationMain: Application(){

    var mNetworkMonitoringUtil: NetworkMonitoringUtil? = null
    override fun onCreate() {
        super.onCreate()


        mNetworkMonitoringUtil = NetworkMonitoringUtil(this)
        mNetworkMonitoringUtil!!.checkNetworkState()
        mNetworkMonitoringUtil!!.registerNetworkCallbackEvents()
    }


}