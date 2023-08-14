package com.example.animeapp.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.models.PopularSeriesData
import com.example.animeapp.models.TvShow
import com.example.animeapp.viewmodel.FavoritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SeriesAdapter(private val favViewModel: FavoritesViewModel, override val lifecycle: Lifecycle) :
   RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>(), LifecycleOwner {


   class SeriesViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
      val textTitle = view.findViewById<TextView>(R.id.textTitle)
      val textDate = view.findViewById<TextView>(R.id.dateTitle)
      val posterImage = view.findViewById<ImageView>(R.id.posterView)
      val favButton = view.findViewById<ImageView>(R.id.fav_item_logo)//SIKINTI BURDA


      @SuppressLint("SetTextI18n")
      fun bindData(data: TvShow) {
         val path: String
         textTitle.text = data.name
         textDate.text = data.start_date
         path = data.image_thumbnail_path

         Glide.with(posterImage).load(path)
            .into(posterImage)


      }
   }


   private var seriesList: List<TvShow>? = null
   private var favList: List<FavoriteData>? = null

   @SuppressLint("NotifyDataSetChanged")
   fun setList(liveData: List<PopularSeriesData>, favList: List<FavoriteData>) {
      liveData.map { this.seriesList = it.tv_shows }
      this.favList = favList
      notifyDataSetChanged()
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.search_items, parent, false)
      return SeriesViewHolder(view)
   }

   override fun getItemCount(): Int {
      return seriesList!!.size
   }

   override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
      holder.setIsRecyclable(false)
      holder.bindData(seriesList!![position])

      findFavorite(position, holder)
      addFavorite(position, holder)


   }


   private fun findFavorite(position: Int, holder: SeriesViewHolder) {

      for (item in seriesList!!) {
         for (item2 in favList!!) {
            if (item.id.toString() == item2.id) {
               if (position == item2.favPosition) {
                  holder.favButton.setImageResource(R.drawable.fav_red)
               }
            }
         }


      }


   }


   private fun addFavorite(position: Int, holder: SeriesViewHolder) {
      val favButton = holder.view.findViewById<ImageView>(R.id.fav_item_logo)

      favButton.setOnClickListener {
         CoroutineScope(Dispatchers.IO).launch {

            val job1: Deferred<Unit> = async {
               favViewModel.addFavorite(
                  FavoriteData(
                     id = seriesList!![position].id.toString(),
                     name = seriesList!![position].name,
                     isFavorite = true,
                     posterLink = seriesList!![position].image_thumbnail_path,
                     favPosition = position,
                     start_date = seriesList?.get(position)?.start_date ?: "-",
                     country = seriesList!![position].country,
                     status = seriesList!![position].status,
                     path = seriesList!![position].image_thumbnail_path,
                     comment = ""
                  )
               )

            }

            job1.await()


         }

         favButton.setImageResource(R.drawable.fav_red)

      }

   }





}
