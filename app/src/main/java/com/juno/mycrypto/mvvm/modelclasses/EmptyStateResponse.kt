package com.juno.mycrypto.mvvm.modelclasses

import com.google.gson.annotations.SerializedName

data class EmptyStateResponse(

    @field:SerializedName("crypto_balance")
    val cryptoBalance: CryptoBalance? = null,

    @field:SerializedName("crypto_prices")
    val cryptoPrices: List<CryptoPricesItem?>? = null,

    @field:SerializedName("all_transactions")
    val allTransactions: List<AllTransactionsItem?>? = null,

    @field:SerializedName("your_crypto_holdings")
    val yourCryptoHoldings: List<YourCryptoHoldingsItem?>? = null
)


