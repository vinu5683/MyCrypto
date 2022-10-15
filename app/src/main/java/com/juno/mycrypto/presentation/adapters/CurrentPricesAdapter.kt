package com.juno.mycrypto.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juno.mycrypto.databinding.CurrentPricesItemLayoutBinding
import com.juno.mycrypto.mvvm.modelclasses.CryptoPricesItem
import com.juno.mycrypto.utils.PhotoUtils.loadUrl

class CurrentPricesAdapter(private val list: List<CryptoPricesItem>) :
    RecyclerView.Adapter<CurrentPricesAdapter.CurrentPricesViewHolder>() {

    class CurrentPricesViewHolder(private val binding: CurrentPricesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: CryptoPricesItem) {
            binding.apply {
                item.logo?.let {
                    ivCryptoIcon.loadUrl(it)
                }
                tvCryptoName.text = item.title
                tvCryptoValue.text = "\$${item.currentPriceInUsd}"
                tvTrendPercentage.text = "1.23%"
                tvHrs.text = "24h"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentPricesViewHolder {
        val binding = CurrentPricesItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CurrentPricesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrentPricesViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size

    }
}