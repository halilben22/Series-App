package com.example.animeapp.models

data class PopularSeriesData(
    val page: Int,
    val pages: Int,
    val total: String,
    val tv_shows: List<TvShow>
)