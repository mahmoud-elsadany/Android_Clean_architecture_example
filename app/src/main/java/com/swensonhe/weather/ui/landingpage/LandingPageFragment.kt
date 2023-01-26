package com.swensonhe.weather.ui.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import com.swensonhe.weather.R
import com.swensonhe.weather.base.BaseFragment

import com.swensonhe.presentation.factory.ViewModelFactory
import com.swensonhe.presentation.viewmodel.LandingPageViewModel
import com.swensonhe.weather.databinding.FragmentLandingPageBinding
import com.swensonhe.weather.util.general.Utils
import com.swensonhe.weather.util.general.Utils.showHideView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class LandingPageFragment : BaseFragment(),ICityClickListener {
    private lateinit var binding: FragmentLandingPageBinding

    @Inject
    lateinit var landingPageModelFactory: ViewModelFactory
    private lateinit var landingPageVM: LandingPageViewModel


    private var networkStatus: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::binding.isInitialized) {
            binding = FragmentLandingPageBinding.inflate(inflater, container, false)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initMainObservations()
    }

    override fun networkStatusChanged(isConnected: Boolean) {
        super.networkStatusChanged(isConnected)
        networkStatus = isConnected
    }

    private fun initMainObservations() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                landingPageVM.exceptionState.collect {
                    print(it)
                    showException(
                        context?.getString(R.string.error_title),
                        context?.getString(R.string.error_mssg),
                        false
                    )
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                landingPageVM.loadingState.collect {
                    showLoading(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                landingPageVM.apiErrorState.collect {

                    print(it.errorMessage)
                    showException(
                        context?.getString(R.string.error_title),
                        context?.getString(R.string.error_mssg),
                        false
                    )

                }
            }
        }
    }


    private fun initUI() {
        landingPageVM =
            ViewModelProvider(this, landingPageModelFactory)[LandingPageViewModel::class.java]


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }


        binding.todayDateTv.text = Utils.getCurrentDate()
        binding.timeTv.text = Utils.getCurrentTime()

        binding.searchIv.setOnClickListener {
            showHideView(binding.searchLayout)
            searching()
        }

        binding.backIv.setOnClickListener {
            showHideView(binding.searchLayout)
        }

        binding.searchListCollapseRl.setOnClickListener {
            showHideView(binding.searchListRv)
        }
        binding.clearTextIv.setOnClickListener {
            binding.searchEt.setText("")
        }


    }

    private fun searching(){
        binding.searchEt.doOnTextChanged { text, start, before, count ->
            if (!text.isNullOrEmpty()) {
                binding.clearTextIv.visibility = View.VISIBLE

                if(text.length >= 3){
                    //do search

                }

            }else{
                binding.clearTextIv.visibility = View.GONE
            }

        }
    }




    override fun onBackPressed(): Boolean {
        requireActivity().finishAffinity()
        return false
    }

    override fun onCityClickListener(city: String) {
        //do action to fetch city data

    }

}