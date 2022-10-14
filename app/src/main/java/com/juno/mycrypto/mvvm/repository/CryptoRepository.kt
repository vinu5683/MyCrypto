package com.juno.mycrypto.mvvm.repository

import com.juno.mycrypto.mvvm.api_services.MyCryptoApiService
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val myCryptoApiService: MyCryptoApiService
){
    fun getMyValueStateData(){

    }
}