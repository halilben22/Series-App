package com.example.animeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyHiltApp:Application() {
   override fun onCreate() {
      super.onCreate()
   }
}