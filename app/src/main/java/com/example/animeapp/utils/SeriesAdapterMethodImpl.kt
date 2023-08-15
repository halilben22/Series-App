package com.example.animeapp.utils

import android.widget.ImageView
import com.example.animeapp.R
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.models.TvShow
import com.example.animeapp.view.adapters.SeriesAdapter
import com.example.animeapp.viewmodel.FavoritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SeriesAdapterMethodImpl: SeriesAdapterMethods {
   override fun findFavorite(
      position: Int,
      holder: SeriesAdapter.SeriesViewHolder,
      seriesList: List<TvShow>,
      favList: List<FavoriteData>
   ) {
      for (item in seriesList) {
         for (item2 in favList) {
            if (item.id.toString() == item2.id) {
               if (position == item2.favPosition) {
                  holder.favButton.setImageResource(R.drawable.fav_red)
               }
            }
         }


      }
   }

   override fun addFavorite(
      position: Int,
      holder: SeriesAdapter.SeriesViewHolder,
      favViewModel: FavoritesViewModel,
      seriesList:List<TvShow>
   ) {
      val favButton = holder.view.findViewById<ImageView>(R.id.fav_item_logo)

      favButton.setOnClickListener {
         CoroutineScope(Dispatchers.IO).launch {

            val job1: Deferred<Unit> = async {
               favViewModel.addFavorite(
                  FavoriteData(
                     id = seriesList[position].id.toString(),
                     name = seriesList[position].name,
                     isFavorite = true,
                     posterLink = seriesList[position].image_thumbnail_path,
                     favPosition = position,
                     start_date = seriesList[position].start_date ?: "-",
                     country = seriesList[position].country,
                     status = seriesList[position].status,
                     path = seriesList[position].image_thumbnail_path,
                     comment = "",
                     rate = "not rated."
                  )
               )

            }

            job1.await()


         }

         favButton.setImageResource(R.drawable.fav_red)

      }
   }
}