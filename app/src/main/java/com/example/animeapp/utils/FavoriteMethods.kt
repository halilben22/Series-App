package com.example.animeapp.utils

import android.content.Context
import android.widget.Button
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.view.adapters.FavoritesAdapter
import com.example.animeapp.viewmodel.FavoritesViewModel

interface FavoriteMethods {

   fun deleteFavorite(position: Int, holder: FavoritesAdapter.FavoritesViewHolder, favoritesViewModel: FavoritesViewModel, favList:List<FavoriteData>)
   fun showDialog(position: Int,holder: FavoritesAdapter.FavoritesViewHolder,favoritesViewModel:FavoritesViewModel,favList:List<FavoriteData>,context: Context)

   fun clearAllFavorites(clearButton:Button,favoritesViewModel: FavoritesViewModel)
}