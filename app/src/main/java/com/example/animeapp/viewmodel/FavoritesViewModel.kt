package com.example.animeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.AsyncListDiffer
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

   }

   fun readFavorites() {
    val list=dao.readFavorites()
      allFavorites.postValue(list)


   }


   fun readFavoritesWithDiffer(differ: AsyncListDiffer<FavoriteData>) {
      differ.submitList(dao.readFavorites())

   }

   fun deleteAllFavorites() {
      dao.deleteAllFavorite()
     // readFavorites()
   }

   fun updateComment(comment: String, id: String) {
      dao.updateFavoriteComment(comment, id)
   }

   fun updateRate(rate: String, id: String) {
      dao.updateFavoriteRate(rate, id)
   }

}