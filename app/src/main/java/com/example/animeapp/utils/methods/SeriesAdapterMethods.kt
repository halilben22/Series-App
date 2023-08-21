package com.example.animeapp.utils.methods

import com.example.animeapp.models.FavoriteData
import com.example.animeapp.models.TvShow
import com.example.animeapp.view.adapters.SeriesAdapter
import com.example.animeapp.viewmodel.FavoritesViewModel

interface SeriesAdapterMethods {

   //All methods in the SeriesAdapter

   fun findFavorite(position: Int, holder: SeriesAdapter.SeriesViewHolder,seriesList: List<TvShow>,favList: List<FavoriteData> )
   fun addFavorite(position: Int, holder: SeriesAdapter.SeriesViewHolder,favoritesViewModel: FavoritesViewModel,seriesList: List<TvShow>)

}