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
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.utils.methods.FavoriteMethodsImpl
import com.example.animeapp.viewmodel.FavoritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavoritesAdapter
@Inject constructor(
   private val favoritesViewModel: FavoritesViewModel,
   private val clearButton: Button,
   val context: Context,
   private val favoriteMethodsImpl: FavoriteMethodsImpl,

   ) :
   RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

   class FavoritesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

      val comment_button = view.findViewById<LinearLayout>(R.id.add_comment)
      val detail_title = view.findViewById<TextView>(com.example.animeapp.R.id.detail_title)
      val detail_start = view.findViewById<TextView>(com.example.animeapp.R.id.detail_start)
      val detail_country = view.findViewById<TextView>(com.example.animeapp.R.id.detail_country)
      val detail_status = view.findViewById<TextView>(com.example.animeapp.R.id.detail_status)
      val posterFav = view.findViewById<ImageView>(com.example.animeapp.R.id.posterFav)
      val rate_text = view.findViewById<TextView>(R.id.rate_text)
      val rate_button = view.findViewById<LinearLayout>(R.id.rate_button)


      @SuppressLint("SetTextI18n")
      fun bindData(data: FavoriteData) {

         detail_title.text = "Name: " + data.name
         detail_start.text = "Start date: " + data.start_date
         detail_country.text = "Country: " + data.country
         detail_status.text = "Status: " + data.status
         rate_text.text = data.rate + "/10"
         val path = data.path


         Glide.with(posterFav).load(path)
            .into(posterFav)

      }


   }

   private var differCallback = object : DiffUtil.ItemCallback<FavoriteData>() {

      override fun areItemsTheSame(oldItem: FavoriteData, newItem: FavoriteData): Boolean {

         return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: FavoriteData, newItem: FavoriteData): Boolean {

         return oldItem == newItem
      }


   }

   val differ = AsyncListDiffer(this, differCallback)


   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
      val view = LayoutInflater.from(parent.context)
         .inflate(R.layout.fav_items, parent, false)
      return FavoritesViewHolder(view)
   }

   override fun getItemCount(): Int {
      return differ.currentList.size
   }

   override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
      holder.bindData(differ.currentList[position])
      favoriteMethodsImpl.clearAllFavorites(clearButton, favoritesViewModel)
      favoriteMethodsImpl.deleteFavorite(position, holder, favoritesViewModel, differ.currentList)
      favoriteMethodsImpl.showCommentDialog(position, holder, favoritesViewModel, differ.currentList, context)


      favoriteMethodsImpl.showRateDialog(position, holder, favoritesViewModel, differ.currentList, context)

   }

}

