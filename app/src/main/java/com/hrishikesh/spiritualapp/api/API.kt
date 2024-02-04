package com.hrishikesh.spiritualapp.api

import retrofit2.http.GET

interface API {
    @GET("/quotes/random")
    suspend fun getRandomQuote() : MutableList<QuoteResponse>
}