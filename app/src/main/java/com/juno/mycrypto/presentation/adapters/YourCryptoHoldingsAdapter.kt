package com.juno.mycrypto.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juno.mycrypto.databinding.EmptyStateHoldingItemLayoutBinding
import com.juno.mycrypto.mvvm.modelclasses.YourCryptoHoldingsItem
import com.juno.mycrypto.utils.PhotoUtils.loadUrl

class YourCryptoHoldingsAdapter(
    private val list: List<YourCryptoHoldingsItem>,
    val listener: CryptoHoldingAdapterListener
) :
    RecyclerView.Adapter<YourCryptoHoldingsAdapter.YourCryptoHoldingsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): YourCryptoHoldingsViewHolder {

        val binding = EmptyStateHoldingItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return YourCryptoHoldingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YourCryptoHoldingsViewHolder, position: Int) {
        holder.setData(list[position], listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class YourCryptoHoldingsViewHolder(private val binding: EmptyStateHoldingItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: YourCryptoHoldingsItem, listener: CryptoHoldingAdapterListener) {

            item.logo?.let {
                binding.ivCryptoIcon.loadUrl(it)
            }
            binding.tvCryptoName.text = item.title

            binding.btnDeposit.setOnClickListener {
                listener.onClickDepositBtn(item)
            }

            binding.btnBuy.setOnClickListener {
                listener.onClickBuyBtn(item)
            }
        }

    }

}

interface CryptoHoldingAdapterListener {
    fun onClickDepositBtn(item: YourCryptoHoldingsItem)
    fun onClickBuyBtn(item: YourCryptoHoldingsItem)
}