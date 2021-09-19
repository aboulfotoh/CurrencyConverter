package com.swensonhe.currencyconverter.ui.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.swensonhe.currencyconverter.R
import kotlinx.android.synthetic.main.fragment_currencies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrenciesFragment : Fragment(), CurrenciesAdapter.OnItemClickListener {

    private var currencies = ArrayList<Currency>()
    private var currenciesAdapter = CurrenciesAdapter()
    private val flagOffset = 0x1F1E6
    private val asciiOffset = 0x41
    private val firstChar = Character.codePointAt("EUR", 0) - asciiOffset + flagOffset
    private val secondChar = Character.codePointAt("EUR", 1) - asciiOffset + flagOffset

    val flag = (String(Character.toChars(firstChar))
            + String(Character.toChars(secondChar)))

    companion object {
        fun newInstance() = CurrenciesFragment()
    }

    private val vm by viewModel<CurrenciesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currencies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getCurrencies()
        tv_header.text = "$flag EUR"
        currenciesAdapter.setOnItemClickListener(this)

        vm.currencies.observe(viewLifecycleOwner, {
            it.rates.forEach { rate ->
                val currency = Currency(rate.key, rate.value)
                currencies.add(currency)
            }
            currenciesAdapter.submitList(currencies)
            currenciesAdapter.notifyDataSetChanged()
        })
        rv_rates.adapter = currenciesAdapter

    }

    override fun onItemClicked(item: Currency) {

    }


}