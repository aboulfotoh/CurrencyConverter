package com.swensonhe.currencyconverter.ui.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.swensonhe.currencyconverter.R
import com.swensonhe.currencyconverter.databinding.FragmentCurrenciesBinding
import com.swensonhe.currencyconverter.utils.ScreenState
import com.swensonhe.currencyconverter.utils.UtilsHelper.getFlag
import kotlinx.android.synthetic.main.no_data_layout.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrenciesFragment : Fragment(), CurrenciesAdapter.OnItemClickListener {

    private var currencies = ArrayList<Currency>()
    private var currenciesAdapter = CurrenciesAdapter()
    private val vm by viewModel<CurrenciesViewModel>()

    private lateinit var binding: FragmentCurrenciesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_currencies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getCurrencies()
        binding.tvHeader.text = getFlag("EUR") + " EUR"
        currenciesAdapter.setOnItemClickListener(this)

        vm.currencies.observe(viewLifecycleOwner, {
            it.rates.forEach { rate ->
                val currency = Currency(rate.key, rate.value)
                currencies.add(currency)
            }
            if (currencies.isNotEmpty()) {
                currenciesAdapter.submitList(currencies)
                currenciesAdapter.notifyDataSetChanged()
                binding.root.pb_loading.visibility = View.GONE
                binding.root.btn_retry.visibility = View.GONE
                binding.root.tv_no_data.visibility = View.GONE
                binding.rvRates.visibility = View.VISIBLE
            } else {
                binding.root.pb_loading.visibility = View.GONE
                binding.root.btn_retry.visibility = View.GONE
                binding.rvRates.visibility = View.GONE
                binding.root.tv_no_data.visibility = View.VISIBLE
            }

        })
        binding.rvRates.adapter = currenciesAdapter

        vm.mutableScreenState.observe(viewLifecycleOwner, {
            when (it?.name) {
                ScreenState.ERROR.name -> {
                    binding.root.pb_loading.visibility = View.GONE
                    binding.root.btn_retry.visibility = View.VISIBLE
                }
            }
        })

        binding.root.btn_retry.setOnClickListener {
            binding.root.pb_loading.visibility = View.VISIBLE
            binding.root.btn_retry.visibility = View.GONE
            binding.rvRates.visibility = View.GONE
            vm.getCurrencies()
        }

    }

    override fun onItemClicked(item: Currency) {
        findNavController().navigate(
            CurrenciesFragmentDirections.actionCurrenciesFragmentToCalculatorFragment(
                item.name,
                item.rate.toFloat()
            )
        )
    }


}