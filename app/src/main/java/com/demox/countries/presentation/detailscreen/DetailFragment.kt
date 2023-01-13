package com.demox.countries.presentation.detailscreen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.demox.countries.databinding.FragmentDetailBinding
import com.demox.countries.plugins.ViewBindingFragment
import com.demox.countries.plugins.loadUrl

class DetailFragment : ViewBindingFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val country = args.country
        binding.apply {
            tvName.text = country.name
            ivFlag.loadUrl(country.flag)
            tvCapital.text = country.capital
            tvRegion.text = country.region
            tvTimezones.text = country.timezones.joinToString(";")
            tvCurrencies.text = country.currencies.map {
                "name:${it.name}(${it.code}) symbol:${it.symbol}"
            }.joinToString(";\n")
        }
    }
}
