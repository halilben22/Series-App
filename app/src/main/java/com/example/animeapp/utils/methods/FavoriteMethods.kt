package com.example.animeapp.utils.methods

import android.content.Context
import android.widget.Button
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.view.adapters.FavoritesAdapter
import com.example.animeapp.viewmodel.FavoritesViewModel


//All methods in the FavoriteAdapter
interface FavoriteMethods {

   fun deleteFavorite(position: Int, holder: FavoritesAdapter.FavoritesViewHolder, favoritesViewModel: FavoritesViewModel, favList:List<FavoriteData>)
   fun showCommentDialog(position: Int,holder: FavoritesAdapter.FavoritesViewHolder,favoritesViewModel:FavoritesViewModel,favList:List<FavoriteData>,context: Context)

   fun clearAllFavorites(clearButton:Button,favoritesViewModel: FavoritesViewModel)

   fun showRateDialog(position: Int,holder: FavoritesAdapter.FavoritesViewHolder,favoritesViewModel:FavoritesViewModel,favList:List<FavoriteData>,context: Context)

}