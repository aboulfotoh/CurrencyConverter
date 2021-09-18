package com.swensonhe.currencyconverter.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */

@BindingAdapter("adapter", "listData")
fun bindRecyclerView(view: RecyclerView, adapter: Any, data: List<Any>?) {
    var recyclerViewAdapter = adapter as androidx.recyclerview.widget.ListAdapter<*, *>
    recyclerViewAdapter.submitList(data as List<Nothing>?)
    recyclerViewAdapter.notifyDataSetChanged()
    if (view.adapter == null) {
        //view.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        view.adapter = recyclerViewAdapter
    } else {
        view.adapter = recyclerViewAdapter
        //adapter.transactions = historyAdapter?.transactions
        view.adapter?.notifyDataSetChanged()
    }
}