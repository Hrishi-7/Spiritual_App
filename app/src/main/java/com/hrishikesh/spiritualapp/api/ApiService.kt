package com.hrishikesh.spiritualapp.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("files/{fileId}")
    suspend fun downloadPdf(@Path("fileId") fileId : String) : Response<ResponseBody>
}