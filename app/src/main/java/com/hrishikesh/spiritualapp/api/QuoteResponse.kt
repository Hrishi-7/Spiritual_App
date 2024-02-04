package com.hrishikesh.spiritualapp.api

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("content")
    val content : String,

    @SerializedName("author")
    val author : String
)