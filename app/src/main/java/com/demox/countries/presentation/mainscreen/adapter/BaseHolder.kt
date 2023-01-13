package com.demox.countries.presentation.mainscreen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.demox.countries.domain.mainscreen.model.Country
import com.demox.countries.plugins.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.country_list_item.*

abstract class BaseHolder(
    view: View,
    private val onItemCkick: (item: Country) -> Unit
) : RecyclerView.ViewHolder(view), LayoutContainer {

    protected fun bindMainInfo(
        item: Country
    ) {
        itemView.setOnClickListener {
            onItemCkick(item)
        }

        tv_name.text = item.name

        iv_flag.loadUrl(item.flag)
    }
}
