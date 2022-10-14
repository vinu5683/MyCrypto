package com.juno.mycrypto.mvvm.repository

import com.juno.mycrypto.mvvm.api_services.MyCryptoApiService
import com.juno.mycrypto.mvvm.modelclasses.EmptyStateResponse
import com.juno.mycrypto.mvvm.modelclasses.ValueStateResponse
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val myCryptoApiService: MyCryptoApiService
) {
    suspend fun getMyValueStateData(): ValueStateResponse {
        return myCryptoApiService.getMyValueStateData()
    }

    suspend fun getMyEmptyStateData(): EmptyStateResponse {
        return myCryptoApiService.getMyEmptyStateData()
    }
}