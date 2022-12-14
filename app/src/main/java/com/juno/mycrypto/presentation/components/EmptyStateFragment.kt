package com.juno.mycrypto.presentation.components

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.juno.mycrypto.R
import com.juno.mycrypto.databinding.FragmentEmptyStateBinding
import com.juno.mycrypto.mvvm.modelclasses.AllTransactionsItem
import com.juno.mycrypto.mvvm.modelclasses.CryptoPricesItem
import com.juno.mycrypto.mvvm.modelclasses.YourCryptoHoldingsItem
import com.juno.mycrypto.mvvm.viewmodels.CryptoEmptyStateViewModel
import com.juno.mycrypto.presentation.adapters.CryptoHoldingAdapterListener
import com.juno.mycrypto.presentation.adapters.CurrentPricesAdapter
import com.juno.mycrypto.presentation.adapters.RecentTransactionsAdapter
import com.juno.mycrypto.presentation.adapters.YourCryptoHoldingsAdapter
import com.juno.mycrypto.utils.CryptoConstants.EMPTYADAPTER
import com.juno.mycrypto.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmptyStateFragment : Fragment(), CryptoHoldingAdapterListener {
    private val emptyViewModel: CryptoEmptyStateViewModel by viewModels()

    private lateinit var binding: FragmentEmptyStateBinding

    private var holdingList = ArrayList<YourCryptoHoldingsItem>()
    private var cryptoPricesList = ArrayList<CryptoPricesItem>()

    private var cryptoHoldingAdapter: YourCryptoHoldingsAdapter =
        YourCryptoHoldingsAdapter(holdingList, this, EMPTYADAPTER)

    private var currentPricesAdapter: CurrentPricesAdapter =
        CurrentPricesAdapter(cryptoPricesList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmptyStateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewsAndAdapters()
        setViewData()
    }

    private fun initViewsAndAdapters() {

        binding.cryptoAccountLayout.btnDeposit.visibility = View.VISIBLE
        binding.cryptoAccountLayout.grpForValueState.visibility = View.GONE

        binding.tvViewAll.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_emptyStateFragment_to_valueStateFragment)
        }

        binding.cryptoAccountLayout.btnDeposit.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_emptyStateFragment_to_valueStateFragment)
        }

        binding.cryptoHoldingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cryptoHoldingAdapter
        }

        binding.cryptoCurrentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = currentPricesAdapter
        }
    }

    private fun setViewData() {
        emptyViewModel.getEmptyStateData()

        emptyViewModel.cryptoEmptyStateData.observe(viewLifecycleOwner) {
            it?.let { resourceWrapper ->
                when (resourceWrapper.status) {
                    Status.SUCCESS -> {
                        Log.d(
                            CryptoHomeActivity.TAG,
                            "Success: Empty Data-> ${resourceWrapper.data}\n\n\n"
                        )

                        resourceWrapper.data?.let { data ->
                            holdingList.clear()
                            holdingList.addAll(data.yourCryptoHoldings as ArrayList<YourCryptoHoldingsItem>)
                            cryptoHoldingAdapter.notifyDataSetChanged()

                            cryptoPricesList.clear()
                            cryptoPricesList.addAll(data.cryptoPrices as ArrayList<CryptoPricesItem>)
                            currentPricesAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }

    override fun onClickDepositBtn(item: YourCryptoHoldingsItem) {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_emptyStateFragment_to_valueStateFragment)
    }

    override fun onClickBuyBtn(item: YourCryptoHoldingsItem) {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_emptyStateFragment_to_valueStateFragment)
    }
}