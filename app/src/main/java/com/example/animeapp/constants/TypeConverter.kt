package com.example.animeapp.constants

import com.example.animeapp.models.PopularSeriesData
import com.example.animeapp.models.TvShow
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Type Converter
object TypeConverter {
   private val gson = Gson()

   // PopularSeriesData to JSON string
   fun popularSeriesDataToJson(popularSeriesData: PopularSeriesData): String {
      return gson.toJson(popularSeriesData)
   }

   // JSON string to PopularSeriesData
   fun jsonToPopularSeriesData(json: String): PopularSeriesData {
      return gson.fromJson(json, PopularSeriesData::class.java)
   }

   // TvShow to JSON string
   fun tvShowToJson(tvShow: TvShow): String {
      return gson.toJson(tvShow)
   }

   // JSON string to TvShow
   fun jsonToTvShow(json: String): TvShow {
      return gson.fromJson(json, TvShow::class.java)
   }

   // List<TvShow> to JSON string
   fun tvShowListToJson(tvShowList: List<TvShow>): String {
      return gson.toJson(tvShowList)
   }

   // JSON string to List<TvShow>
   fun jsonToTvShowList(json: String): List<TvShow> {
      val listType = object : TypeToken<List<TvShow>>() {}.type
      return gson.fromJson(json, listType)
   }
}