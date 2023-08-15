package com.example.animeapp.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.utils.FavoriteMethodsImpl
import com.example.animeapp.viewmodel.FavoritesViewModel

class FavoritesAdapter constructor(
   private val favoritesViewModel: FavoritesViewModel,
   private val clearButton: Button,
   val context: Context,
   val favoriteMethodsImpl: FavoriteMethodsImpl
) :
   RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
   class FavoritesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

      val comment_button = view.findViewById<LinearLayout>(R.id.add_comment)
      val detail_title = view.findViewById<TextView>(R.id.detail_title)
      val detail_start = view.findViewById<TextView>(R.id.detail_start)
      val detail_country = view.findViewById<TextView>(R.id.detail_country)
      val detail_status = view.findViewById<TextView>(R.id.detail_status)
      val posterFav = view.findViewById<ImageView>(R.id.posterFav)


      @SuppressLint("SetTextI18n")
      fun bindData(data: FavoriteData) {

         detail_title.text = "Name: " + data.name
         detail_start.text = "Start date: " + data.start_date
         detail_country.text = "Country: " + data.country
         detail_status.text = "Status: " + data.status
         val path = data.path


         Glide.with(posterFav).load(path)
            .into(posterFav)


      }

   }

   private var favList: List<FavoriteData>? = null


   @SuppressLint("NotifyDataSetChanged")
   fun setList(favList: List<FavoriteData>) {

      this.favList = favList
      notifyDataSetChanged()
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_items, parent, false)
      return FavoritesViewHolder(view)
   }

   override fun getItemCount(): Int {
      return favList?.size ?: 0
   }

   override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
      holder.setIsRecyclable(false)
      holder.bindData(favList!![position])
      favoriteMethodsImpl.clearAllFavorites(clearButton, favoritesViewModel)
      favoriteMethodsImpl.deleteFavorite(position, holder, favoritesViewModel, favList!!)
      favoriteMethodsImpl.showDialog(position, holder, favoritesViewModel, favList!!, context)

   }


}
