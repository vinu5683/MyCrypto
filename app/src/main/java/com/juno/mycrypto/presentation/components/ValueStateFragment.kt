package com.juno.mycrypto.presentation.components

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juno.mycrypto.R
import com.juno.mycrypto.databinding.FragmentEmptyStateBinding
import com.juno.mycrypto.databinding.FragmentValueStateBinding
import com.juno.mycrypto.mvvm.modelclasses.AllTransactionsItem
import com.juno.mycrypto.mvvm.modelclasses.CryptoPricesItem
import com.juno.mycrypto.mvvm.modelclasses.YourCryptoHoldingsItem
import com.juno.mycrypto.mvvm.viewmodels.CryptoValueStateViewModel
import com.juno.mycrypto.presentation.adapters.CurrentPricesAdapter
import com.juno.mycrypto.presentation.adapters.RecentTransactionsAdapter
import com.juno.mycrypto.presentation.adapters.YourCryptoHoldingsAdapter
import com.juno.mycrypto.utils.CryptoConstants.VALUEADAPTER
import com.juno.mycrypto.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ValueStateFragment : Fragment() {

    private val valueViewModel: CryptoValueStateViewModel by viewModels()

    lateinit var binding: FragmentValueStateBinding

    private var transactionList = ArrayList<AllTransactionsItem>()
    private var holdingList = ArrayList<YourCryptoHoldingsItem>()
    private var cryptoPricesList = ArrayList<CryptoPricesItem>()

    private var recentTransactionsAdapter: RecentTransactionsAdapter =
        RecentTransactionsAdapter(transactionList)
    private var currentPricesAdapter: CurrentPricesAdapter =
        CurrentPricesAdapter(cryptoPricesList)
    private var cryptoHoldingAdapter: YourCryptoHoldingsAdapter =
        YourCryptoHoldingsAdapter(holdingList, null, VALUEADAPTER)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentValueStateBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewsAndAdapters()
        setViewData()
    }

    private fun initViewsAndAdapters() {
        binding.cryptoAccountLayout.grpForValueState.visibility = View.VISIBLE
        binding.cryptoAccountLayout.btnDeposit.visibility = View.GONE
        binding.cryptoHoldingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cryptoHoldingAdapter
        }

        binding.cryptoTransactionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recentTransactionsAdapter
        }

        binding.cryptoCurrentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = currentPricesAdapter
        }
    }

    private fun setViewData() {
        valueViewModel.getValueStateData()

        valueViewModel.cryptoValueStateData.observe(viewLifecycleOwner) {
            it?.let { resourceWrapper ->
                when (resourceWrapper.status) {
                    Status.SUCCESS -> {
                        Log.d(
                            CryptoHomeActivity.TAG,
                            "Success: Empty Data-> ${resourceWrapper.data}\n\n\n"
                        )

                        resourceWrapper.data?.let { data ->
                            binding.cryptoAccountLayout.tvHoldingValue.text =
                                data.cryptoBalance?.currentBalInUsd

                            transactionList.clear()
                            transactionList.addAll(data.allTransactions as ArrayList<AllTransactionsItem>)
                            recentTransactionsAdapter.notifyDataSetChanged()

                            cryptoPricesList.clear()
                            cryptoPricesList.addAll(data.cryptoPrices as ArrayList<CryptoPricesItem>)
                            currentPricesAdapter.notifyDataSetChanged()

                            holdingList.clear()
                            holdingList.addAll(data.yourCryptoHoldings as ArrayList<YourCryptoHoldingsItem>)
                            cryptoHoldingAdapter.notifyDataSetChanged()

                        }
                    }
                }
            }
        }
    }
}