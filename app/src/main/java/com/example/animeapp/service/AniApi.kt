package com.example.animeapp.service

import com.example.animeapp.models.PopularSeriesData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface AniApi {

   @GET("/api/most-popular")
  fun getAllSeries(@Query("page") page:String):Call<PopularSeriesData>


  @GET("/api/search")
  fun searchSeries(@Query("q") query: String):Call<PopularSeriesData>
}