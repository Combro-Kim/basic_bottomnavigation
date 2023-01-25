package com.example.poin_df

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/d5af0d0f-0318-4afb-8eef-fe4c8145f560")
    fun getHouseList(): Call<HouseDto>
}