package com.juno.mycrypto.mvvm.api_services

import com.juno.mycrypto.utils.CryptoConstants
import retrofit2.http.GET

interface MyCryptoApiService {

    @GET(CryptoConstants.valueStateHomeApi)
    suspend fun getMyValueStateData(){

    }

}