package com.swensonhe.currencyconverter.ui.currencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swensonhe.currencyconverter.databinding.ItemCurrencyBinding


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

        private val flagOffset = 0x1F1E6
        private val asciiOffset = 0x41
        fun bind(item: Currency, mListener: OnItemClickListener) {

            val firstChar = Character.codePointAt(item.name, 0) - asciiOffset + flagOffset
            val secondChar = Character.codePointAt(item.name, 1) - asciiOffset + flagOffset

            val flag = (String(Character.toChars(firstChar))
                    + String(Character.toChars(secondChar)))
            binding.currency = item
            binding.tvCurrency.text = flag + " " + item.name
            binding.tvRate.text = item.rate.toString()
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
