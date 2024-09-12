package com.sanket.newsreader.ui.di.core

import com.sanket.newsreader.data.NewsAPIService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val baseUrl:String) {
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl).build()
    }

    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit):NewsAPIService{
        return retrofit.create(NewsAPIService::class.java)
    }
}