package com.swensonhe.currencyconverter.ui.currencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swensonhe.currencyconverter.databinding.ItemCurrencyBinding
import com.swensonhe.currencyconverter.utils.UtilsHelper.getFlag


class CurrenciesAdapter :
    ListAdapter<Currency, CurrenciesAdapter.ViewHolder>(CurrenciesDiffUtils()) {

    private var mListener: OnItemClickListener? = null

    class CurrenciesDiffUtils : DiffUtil.ItemCallback<Currency>() {

        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.form(
            parent
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), mListener!!)
    }

    class ViewHolder
    constructor(
        val
        binding: ItemCurrencyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Currency, mListener: OnItemClickListener) {
            binding.currency = item
            binding.tvCurrency.text = getFlag(item.name) + " " + item.name
            binding.tvRate.text = item.rate.toString()
            binding.root.setOnClickListener {
                mListener.onItemClicked(item)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun form(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemCurrencyBinding.inflate(inflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(item: Currency)
    }

    fun setOnItemClickListener(mListener: OnItemClickListener) {
        this.mListener = mListener
    }
}
