package com.example.animeapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TvShow(
    @SerializedName("country")
    val country: String,
    @SerializedName("end_date")
    val end_date: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_thumbnail_path")
    val image_thumbnail_path: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("network")
    val network: String,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("start_date")
    val start_date: String,
    @SerializedName("status")
    val status: String
):Serializable