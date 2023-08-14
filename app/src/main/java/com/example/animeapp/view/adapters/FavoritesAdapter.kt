package com.example.animeapp.view.adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.viewmodel.FavoritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavoritesAdapter constructor(
   val favoritesViewModel: FavoritesViewModel,
   val clearButton: Button,
   val context: Context
) :
   RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
   class FavoritesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


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
      holder.bindData(favList!![position])
      clearAll(holder)
      deleteFavorite(position, holder)
showDialog(holder)

   }

   private fun clearAll(holder: FavoritesViewHolder) {

      clearButton.setOnClickListener {

         favoritesViewModel.deleteAllFavorites()


      }
   }


   private fun deleteFavorite(position: Int, holder: FavoritesViewHolder) {
      val favButton = holder.view.findViewById<ImageView>(R.id.heart_button)

      favButton.setOnClickListener {

         favButton.setImageResource(R.drawable.baseline_favorite_border_24)
         CoroutineScope(Dispatchers.IO).launch {
            delay(180)

            val job1: Deferred<Unit> = async {
               favoritesViewModel.deleteFavorite(
                  favList!![position]
               )
               println(favList!!)


            }

            job1.await()


         }



      }

   }



   private fun showDialog(holder: FavoritesViewHolder){

      val button=holder.view.findViewById<LinearLayout>(R.id.add_comment)
      button.setOnClickListener {
         val dialog=Dialog(context)
         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
         dialog.setContentView(R.layout.fav_bottom_sheet)
         dialog.show()
         dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
         dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
         dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
         dialog.window!!.setGravity(Gravity.BOTTOM)
      }

   }
   }
