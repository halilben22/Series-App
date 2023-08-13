package com.example.animeapp.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animeapp.db.dao.FavoriteDao
import com.example.animeapp.models.FavoriteData


@Database(entities = [FavoriteData::class], version = 2)
abstract class FavoriteDatabase: RoomDatabase() {


   abstract fun getDao(): FavoriteDao


   companion object {

      private var dbInstance: FavoriteDatabase? = null
      fun getAppDB(context: Context): FavoriteDatabase{
         if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
               context.applicationContext,
               FavoriteDatabase::class.java,
               "favorites_database"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
         }

         return dbInstance!!
      }

   }


}