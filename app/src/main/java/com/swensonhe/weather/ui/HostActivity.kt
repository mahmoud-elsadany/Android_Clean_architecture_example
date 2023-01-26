package com.swensonhe.weather.ui

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.swensonhe.weather.R
import com.swensonhe.weather.base.BaseActivity
import com.swensonhe.weather.base.BaseFragment
import com.swensonhe.weather.databinding.ActivityHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : BaseActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityHostBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //disble dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)



        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.host_nav_graph) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onBackPressed() {

        val fragment =
            supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.get(
                0
            )
        if (fragment is BaseFragment) {
            if (fragment.onBackPressed())
                super.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }


}