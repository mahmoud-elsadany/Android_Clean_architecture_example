package com.swensonhe.weather.application

import android.app.Application

import com.swensonhe.common.CommonConstants.APP_VERSION
import com.swensonhe.weather.util.general.Utils.getAppVersion
import com.swensonhe.weather.util.network.NetworkMonitoringUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationMain: Application(){

    var mNetworkMonitoringUtil: NetworkMonitoringUtil? = null
    override fun onCreate() {
        super.onCreate()


        APP_VERSION = getAppVersion(this)

        mNetworkMonitoringUtil = NetworkMonitoringUtil(this)
        mNetworkMonitoringUtil!!.checkNetworkState()
        mNetworkMonitoringUtil!!.registerNetworkCallbackEvents()
    }


}