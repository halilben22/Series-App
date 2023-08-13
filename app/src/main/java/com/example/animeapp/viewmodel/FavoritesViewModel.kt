package com.example.animeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animeapp.db.dao.FavoriteDao
import com.example.animeapp.models.FavoriteData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(private val dao: FavoriteDao) : ViewModel() {

   val allFavorites: MutableLiveData<MutableList<FavoriteData>> = MutableLiveData()


   fun favoriteObserver(): MutableLiveData<MutableList<FavoriteData>> {

      return allFavorites
   }

   fun addFavorite(favorite: FavoriteData) {
      dao.addFavorite(favorite)
      dao.readFavorites()
   }

   fun deleteFavorite(favorite: FavoriteData) {
      dao.deleteFavorite(favorite)
      readFavorites()
   }

   fun readFavorites() {
      val list = dao.readFavorites()
      allFavorites.postValue(list)
   }

   fun deleteAllFavorites() {
      dao.deleteAllFavorite()
readFavorites()
   }

}