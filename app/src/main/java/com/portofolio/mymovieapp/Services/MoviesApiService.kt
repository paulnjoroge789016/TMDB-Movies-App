package com.portofolio.moviesapp.Services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.portofolio.moviesapp.Retrofit.RetrofitInterface
import com.portofolio.mymovieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by paul on 12/26/2019 at 1:49 PM.
 */
object MoviesApiService {

    val interceptor = Interceptor{chain ->
        val apiKey = "2e417461a5c52546847623467482f881"
        val newUrl= chain.request().url().newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("language", "en-US")
            .addQueryParameter("page", "1")
            .build()

        val request= chain.request().newBuilder().url(newUrl)
            .build()

        chain.proceed(request)
    }

    private val loggingInterceptor =  HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    val apiClient=
        if(BuildConfig.DEBUG){ OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()}
        else{
            OkHttpClient.Builder()
                .addInterceptor(interceptor).build()
        }

    private const val baseUrl = "https://api.themoviedb.org/3/"

     private fun getRetrofit(): Retrofit{
        return Retrofit.Builder().client(apiClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val moviesApi: RetrofitInterface = getRetrofit().create(RetrofitInterface::class.java)
}