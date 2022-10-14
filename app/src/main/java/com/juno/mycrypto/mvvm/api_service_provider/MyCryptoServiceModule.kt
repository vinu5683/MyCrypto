package com.juno.mycrypto.mvvm.api_service_provider

import com.juno.mycrypto.mvvm.api_services.MyCryptoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MyCryptoServiceModule {

    @Provides
    @Singleton
    fun providesMyCryptoApi(retrofit: Retrofit): MyCryptoApiService {
        return retrofit.create(MyCryptoApiService::class.java)
    }
}