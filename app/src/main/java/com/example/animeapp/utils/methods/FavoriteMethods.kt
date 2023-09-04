package com.example.animeapp.utils.methods

import android.content.Context
import android.widget.Button
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncListDiffer
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.view.adapters.FavoritesAdapter
import com.example.animeapp.viewmodel.FavoritesViewModel


//All methods in the FavoriteAdapter
interface FavoriteMethods {

   fun deleteFavorite(position: Int, holder: FavoritesAdapter.FavoritesViewHolder, favoritesViewModel: FavoritesViewModel, favList:List<FavoriteData>,differ: AsyncListDiffer<FavoriteData>)
   fun showCommentDialog(position: Int,holder: FavoritesAdapter.FavoritesViewHolder,favoritesViewModel:FavoritesViewModel,favList:List<FavoriteData>,context: Context,differ: AsyncListDiffer<FavoriteData>)

   fun clearAllFavorites(clearButton:Button,favoritesViewModel: FavoritesViewModel,differ: AsyncListDiffer<FavoriteData>)

   fun showRateDialog(position: Int,holder: FavoritesAdapter.FavoritesViewHolder,favoritesViewModel:FavoritesViewModel,favList:List<FavoriteData>,context: Context,differ: AsyncListDiffer<FavoriteData>)

}