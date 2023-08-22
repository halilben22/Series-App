package com.example.animeapp.module

import android.app.Application
import com.example.animeapp.constants.Constants.baseUrl
import com.example.animeapp.db.dao.FavoriteDao
import com.example.animeapp.db.database.FavoriteDatabase

import com.example.animeapp.service.AniApi
import com.example.animeapp.view.adapters.FavoritesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   @Singleton
   @Provides
   fun getAppDBFav(context: Application): FavoriteDatabase{
      return FavoriteDatabase.getAppDB(context)
   }


   @Singleton
   @Provides
   fun getFavDao(appDB:FavoriteDatabase): FavoriteDao {
      return appDB.getDao()
   }



   @Singleton
   @Provides

   fun getRetrofit(): Retrofit {
      return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
         .build()
   }

   @Singleton
   @Provides
   fun getRetrofitServiceInstance(retrofit: Retrofit): AniApi {
      return retrofit.create(AniApi::class.java)
   }

}