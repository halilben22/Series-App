package com.example.animeapp.module

import com.example.animeapp.constants.Constants.baseUrl
import com.example.animeapp.service.AniApi
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