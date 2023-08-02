package com.example.animeapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PopularSeriesData(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: String,
    @SerializedName("tv_shows")
    val tv_shows: List<TvShow>
):Serializable