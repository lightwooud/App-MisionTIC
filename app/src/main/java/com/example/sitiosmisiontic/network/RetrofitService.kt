package com.example.sitiosmisiontic.network

import com.example.sitiosmisiontic.model.SiteModel
import retrofit2.http.GET

interface RetrofitService {

    @GET("6e8f46e1-8175-40ed-9075-5d9d765b4bda")
    suspend fun getDataFromApi(): SiteModel
}