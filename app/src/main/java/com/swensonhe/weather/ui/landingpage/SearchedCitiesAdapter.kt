package com.swensonhe.weather.ui.landingpage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.swensonhe.common.model.landingpage.cities_responseItem
import com.swensonhe.weather.databinding.ItemSpinnerBinding

class SearchedCitiesAdapter (
    val context: Context,
    private val allCities: ArrayList<cities_responseItem>,
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
        fun bind(obj: cities_responseItem, context: Context) {
            binding.cityNameTv.text = obj.name
            binding.countryNameTv.text = " - ${obj.country}"


            itemView.setOnClickListener { mICityClickListener.onCityClickListener(obj) }
        }
    }
}

interface ICityClickListener {
    fun onCityClickListener(city: cities_responseItem)
}