package com.demox.countries.presentation.mainscreen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.demox.countries.domain.mainscreen.model.Country
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CountryAdapter(
    onItemClick: (item: Country) -> Unit
) : AsyncListDifferDelegationAdapter<Country>(CountryDiffUtilCallBack()) {
    init {
        delegatesManager.addDelegate(CountryDelegateAdapter(onItemClick))
    }

    class CountryDiffUtilCallBack : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}
