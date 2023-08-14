package com.example.animeapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animeapp.models.FavoriteData


@Dao
interface FavoriteDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun addFavorite(favData: FavoriteData)


   @Delete
   fun deleteFavorite(favData: FavoriteData)


   @Query("SELECT * FROM favorites")
   fun readFavorites(): MutableList<FavoriteData>

   @Query("DELETE FROM favorites")
   fun deleteAllFavorite()

   @Query("UPDATE favorites SET comment=:comment WHERE id=:id")
   fun updateFavoriteComment(comment:String,id:String)


}