package com.demox.countries.presentation.mainscreen.adapter

import android.view.View
import android.view.ViewGroup
import com.demox.countries.R
import com.demox.countries.domain.mainscreen.model.Country
import com.demox.countries.plugins.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class CountryDelegateAdapter(private val onItemClick: (item: Country) -> Unit) : AbsListItemAdapterDelegate<Country, Country, CountryDelegateAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): CountryViewHolder {
        return CountryViewHolder(parent.inflate(R.layout.country_list_item), onItemClick)
    }
    class CountryViewHolder(
        view: View,
        onItemClick: (item: Country) -> Unit
    ) : BaseHolder(view, onItemClick) {

        fun bind(item: Country) {
            bindMainInfo(item)
        }
        override val containerView: View
            get() = itemView
    }

    override fun isForViewType(
        item: Country,
        items: MutableList<Country>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: Country,
        holder: CountryViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
