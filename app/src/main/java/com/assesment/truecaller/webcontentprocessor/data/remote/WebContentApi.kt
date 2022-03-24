package com.assesment.truecaller.webcontentprocessor.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface WebContentApi {

    @GET("life-as-an-android-engineer/")
    suspend fun getContent(): Response<String>

    companion object {
        const val BASE_URL = "https://blog.truecaller.com/2018/01/22/"
    }
}