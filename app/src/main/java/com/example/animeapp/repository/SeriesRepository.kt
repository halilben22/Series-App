package com.example.animeapp.repository

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import com.example.animeapp.R
import com.example.animeapp.models.PopularSeriesData
import com.example.animeapp.models.TvShow
import com.example.animeapp.service.AniApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class SeriesRepository @Inject constructor(private val seriesApi: AniApi) {

   fun getAllSeries(@Query("page") page: String,liveData: MutableLiveData<PopularSeriesData>) {
      return seriesApi.getAllSeries(page).enqueue(object : Callback<PopularSeriesData>{
         override fun onResponse(
            call: Call<PopularSeriesData>,
            response: Response<PopularSeriesData>
         ) {
            if (response.isSuccessful){
               liveData.postValue(response.body())
            }
         }

         override fun onFailure(call: Call<PopularSeriesData>, t: Throwable) {
         println("error!")
         }
      })
   }



   fun getSearchSeries(@Query("q") query: String,liveData: MutableLiveData<PopularSeriesData>) {
      return seriesApi.searchSeries(query).enqueue(object : Callback<PopularSeriesData>{
         override fun onResponse(
            call: Call<PopularSeriesData>,
            response: Response<PopularSeriesData>
         ) {
            if (response.isSuccessful){
               liveData.postValue(response.body())
            }
         }

         override fun onFailure(call: Call<PopularSeriesData>, t: Throwable) {
            println("error!")
         }
      })
   }



   fun searchSeries(@Query("q") query: String): Call<PopularSeriesData> {
      return seriesApi.searchSeries(query)
   }




}