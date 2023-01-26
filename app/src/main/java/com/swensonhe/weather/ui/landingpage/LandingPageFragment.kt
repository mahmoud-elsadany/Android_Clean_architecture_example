package com.swensonhe.weather.ui.landingpage

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.CustomTarget
import com.swensonhe.common.model.landingpage.cities_responseItem

import com.swensonhe.weather.R
import com.swensonhe.weather.base.BaseFragment

import com.swensonhe.presentation.factory.ViewModelFactory
import com.swensonhe.presentation.viewmodel.LandingPageViewModel
import com.swensonhe.weather.BuildConfig
import com.swensonhe.weather.databinding.FragmentLandingPageBinding
import com.swensonhe.weather.util.general.Utils
import com.swensonhe.weather.util.general.Utils.showHideView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class LandingPageFragment : BaseFragment(), ICityClickListener {
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


        landingPageVM.getCityWeather(BuildConfig.WEATHER_API_KEY, "San Francisco")


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                landingPageVM.current_city_weather.collect {

                    binding.cityNameTv.text = it.location.name
                    binding.todayWeatherFTv.text =
                        "${it.current.temp_f} " + getString(R.string.fahrenheit)
                    binding.todayWindTv.text = "${it.current.wind_mph} " + getString(R.string.mph)
                    binding.todayHumidityTv.text =
                        "${it.current.humidity} " + getString(R.string.percentage)
                    binding.todayWeatherDescTv.text = it.current.condition.text
                    binding.todayWeatherDescTv.text = it.current.condition.text
                    binding.todayDateTv.text =
                        Utils.getCurrentDate(it.location.localtime_epoch.toLong())
                    binding.timeTv.text = Utils.getCurrentTime(it.location.localtime_epoch.toLong())

                    Glide.with(this@LandingPageFragment)
                        .asBitmap()
                        .load("https:" + it.current.condition.icon)
                        .into(object : CustomTarget<Bitmap>() {
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                            ) {
                                binding.todayWeatherIv.setImageBitmap(resource)
                                binding.todayWeatherStatusIv.setImageBitmap(resource)
                                binding.tomorrowWeatherStatusIv.setImageBitmap(resource)
                                binding.fridayWeatherStatusIv.setImageBitmap(resource)
                            }

                            override fun onLoadCleared(placeholder: Drawable?) {
                            }


                        })

                    binding.todayWeatherStatusTv.text =
                        "${it.current.temp_c} " + getString(R.string.degree) + " /${it.current.temp_f} " + getString(
                            R.string.fahrenheit
                        )
                    binding.tomorrowWeatherStatusTv.text =
                        "${it.current.temp_c} " + getString(R.string.degree) + " /${it.current.temp_f} " + getString(
                            R.string.fahrenheit
                        )
                    binding.fridayWeatherStatusTv.text =
                        "${it.current.temp_c} " + getString(R.string.degree) + " /${it.current.temp_f} " + getString(
                            R.string.fahrenheit
                        )

                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                landingPageVM.searched_cities.collect {
                    setSearchList(it)
                }

            }
        }



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

    private fun searching() {
        binding.searchEt.doOnTextChanged { text, start, before, count ->
            if (!text.isNullOrEmpty()) {
                binding.clearTextIv.visibility = View.VISIBLE

                if (text.length >= 3) {
                    //do search
                    landingPageVM.getSearchedCities(BuildConfig.WEATHER_API_KEY, text.toString())

                }

            } else {
                binding.clearTextIv.visibility = View.GONE
            }

        }
    }

    private fun setSearchList(allCities: ArrayList<cities_responseItem>){
        binding.searchListRv.adapter = SearchedCitiesAdapter(requireContext(), allCities, this@LandingPageFragment)
    }

    override fun onBackPressed(): Boolean {
        requireActivity().finishAffinity()
        return false
    }

    override fun onCityClickListener(city: cities_responseItem) {
        //do action to fetch city data
        showHideView(binding.searchLayout)
        landingPageVM.getCityWeather(BuildConfig.WEATHER_API_KEY, cityName = city.name)
    }

}