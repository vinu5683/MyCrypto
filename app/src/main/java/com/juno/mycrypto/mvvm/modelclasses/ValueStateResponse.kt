package com.juno.mycrypto.mvvm.modelclasses

import com.google.gson.annotations.SerializedName

data class ValueStateResponse(

    @field:SerializedName("crypto_balance")
    val cryptoBalance: CryptoBalance? = null,

    @field:SerializedName("crypto_prices")
    val cryptoPrices: List<CryptoPricesItem?>? = null,

    @field:SerializedName("all_transactions")
    val allTransactions: List<AllTransactionsItem?>? = null,

    @field:SerializedName("your_crypto_holdings")
    val yourCryptoHoldings: List<YourCryptoHoldingsItem?>? = null
)

data class CryptoBalance(

    @field:SerializedName("subtitle")
    val subtitle: String? = null,

    @field:SerializedName("current_bal_in_usd")
    val currentBalInUsd: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)


data class CryptoPricesItem(

    @field:SerializedName("logo")
    val logo: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("current_price_in_usd")
    val currentPriceInUsd: String? = null
)

data class YourCryptoHoldingsItem(

    @field:SerializedName("current_bal_in_usd")
    val currentBalInUsd: String? = null,

    @field:SerializedName("logo")
    val logo: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("current_bal_in_token")
    val currentBalInToken: String? = null
)

data class AllTransactionsItem(

    @field:SerializedName("txn_sub_amount")
    val txnSubAmount: String? = null,

    @field:SerializedName("txn_logo")
    val txnLogo: String? = null,

    @field:SerializedName("txn_time")
    val txnTime: String? = null,

    @field:SerializedName("txn_amount")
    val txnAmount: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)
