package com.example.animeapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
data class FavoriteData(
@PrimaryKey
   val id: String,
   var name: String,
   val posterLink: String,
   val isFavorite: Boolean,
   val favPosition:Int,
   val start_date:String,
   val country:String,
   val status:String,
   val path:String,
   val comment:String
)