package com.hrishikesh.spiritualapp.api

import com.hrishikesh.spiritualapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Retrofit {
    private val BASE_URL = "https://api.quotable.io"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val quoteApiService = retrofit.create(API::class.java)

    suspend fun getRandomQuote() : List<QuoteResponse> {
        return quoteApiService.getRandomQuote()
    }
}