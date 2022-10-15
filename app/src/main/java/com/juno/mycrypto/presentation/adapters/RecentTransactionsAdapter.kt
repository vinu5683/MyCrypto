package com.juno.mycrypto.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juno.mycrypto.databinding.RecentTransactionItemBinding
import com.juno.mycrypto.mvvm.modelclasses.AllTransactionsItem
import com.juno.mycrypto.utils.PhotoUtils.loadUrl

class RecentTransactionsAdapter(private val list: List<AllTransactionsItem>) :
    RecyclerView.Adapter<RecentTransactionsAdapter.RecentTransactionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentTransactionViewHolder {
        val binding = RecentTransactionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecentTransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentTransactionViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class RecentTransactionViewHolder(private val binding: RecentTransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: AllTransactionsItem) {
            binding.apply {
                ivCryptoIcon.loadUrl(item.txnLogo ?: "")
                tvTransactionTitle.text = item.title
                tvTransactionSubTitle.text = item.txnTime
                tvTransactionValue.text = item.txnAmount
                tvTransactionChange.text = item.txnSubAmount
            }
        }
    }
}