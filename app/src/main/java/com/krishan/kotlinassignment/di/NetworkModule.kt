package com.krishan.kotlinassignment.di

import com.krishan.kotlinassignment.adapter.ProductAdapter
import com.krishan.kotlinassignment.api.NetworkAPI
import com.krishan.kotlinassignment.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkAPI(retrofit: Retrofit) : NetworkAPI {
        return retrofit.create(NetworkAPI::class.java)
    }
}