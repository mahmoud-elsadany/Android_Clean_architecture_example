package com.swensonhe.weather.ui.landingpage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.swensonhe.weather.databinding.ItemSpinnerBinding

class SearchedCitiesAdapter (
    val context: Context,
    private val allCities: List<String>,
    var mICityClickListener: ICityClickListener
) : RecyclerView.Adapter<SearchedCitiesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemSpinnerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = allCities.size

    override fun onBindViewHolder(holder: SearchedCitiesAdapter.ViewHolder, position: Int) {
        // set defaults for normal design not expandable
        allCities[position].let { holder.bind(it, context) }

    }

    inner class ViewHolder(private val binding: ItemSpinnerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: String, context: Context) {
            binding.cityNameTv.text = city
            binding.countryNameTv.text = " - $city"
        }
    }
}

interface ICityClickListener {
    fun onCityClickListener(city: String)
}