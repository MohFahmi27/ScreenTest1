package com.mfahmi.screentest1.data.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v2/596dec7f0f000023032b8017")
    fun getGuestName(): Call<List<Guest>>
}