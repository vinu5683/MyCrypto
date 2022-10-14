package com.juno.mycrypto.di

import com.juno.mycrypto.utils.CryptoConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    var interceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder().baseUrl(CryptoConstants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build();
    }
}