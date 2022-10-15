package com.juno.mycrypto.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juno.mycrypto.databinding.EmptyStateHoldingItemLayoutBinding
import com.juno.mycrypto.databinding.ValueStateHoldingItemLayoutBinding
import com.juno.mycrypto.mvvm.modelclasses.YourCryptoHoldingsItem
import com.juno.mycrypto.utils.CryptoConstants.EMPTYADAPTER
import com.juno.mycrypto.utils.PhotoUtils.loadUrl

class YourCryptoHoldingsAdapter(
    private val list: List<YourCryptoHoldingsItem>,
    private val listener: CryptoHoldingAdapterListener? = null,
    private val type: String
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (type == EMPTYADAPTER) EmptyCryptoHoldingsViewHolder(
            EmptyStateHoldingItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) else ValueCryptoHoldingsViewHolder(
            ValueStateHoldingItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EmptyCryptoHoldingsViewHolder)
            holder.setData(list[position], listener)
        else if (holder is ValueCryptoHoldingsViewHolder)
            holder.setData(item = list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class EmptyCryptoHoldingsViewHolder(private val binding: EmptyStateHoldingItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: YourCryptoHoldingsItem, listener: CryptoHoldingAdapterListener?) {

            item.logo?.let {
                binding.ivCryptoIcon.loadUrl(it)
            }
            binding.tvCryptoName.text = item.title

            listener?.let {
                binding.btnDeposit.setOnClickListener {
                    listener.onClickDepositBtn(item)
                }

                binding.btnBuy.setOnClickListener {
                    listener.onClickBuyBtn(item)
                }
            }
        }
    }

    class ValueCryptoHoldingsViewHolder(private val binding: ValueStateHoldingItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: YourCryptoHoldingsItem) {
            binding.apply {
                item.logo?.let {
                    ivCryptoIcon.loadUrl(it)
                }
                tvCryptoTitle.text = item.title
                tvHoldingValue.text = "\$${item.currentBalInUsd}"
                tvHoldingSubTitle.text = item.currentBalInToken
                tvHoldingSubTitle.text = item.currentBalInToken
            }
        }
    }
}

interface CryptoHoldingAdapterListener {
    fun onClickDepositBtn(item: YourCryptoHoldingsItem)
    fun onClickBuyBtn(item: YourCryptoHoldingsItem)
}