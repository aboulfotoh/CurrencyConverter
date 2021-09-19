package com.swensonhe.currencyconverter.ui.calculation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.swensonhe.currencyconverter.R
import com.swensonhe.currencyconverter.databinding.FragmentCalulatorBinding
import com.swensonhe.currencyconverter.utils.UtilsHelper.getFlag

class CalculatorFragment : Fragment() {

    private val args by navArgs<CalculatorFragmentArgs>()
    private lateinit var binding: FragmentCalulatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calulator, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvFromCurrency.text = getFlag("EUR")
        binding.tvToCurrency.text = getFlag(args.currency)
        binding.etAmount.addTextChangedListener {
            if (it?.isNotEmpty()!!) {
                val result = binding.etAmount.text?.toString()?.toDouble()!! * args.rate.toDouble()
                binding.tvRate.text = result.toString()
            } else
                binding.tvRate.text = ""

        }
    }

}