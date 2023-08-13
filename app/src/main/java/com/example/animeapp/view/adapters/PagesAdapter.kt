package com.example.animeapp.view.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animeapp.R
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.models.PageData
import com.example.animeapp.viewmodel.FavoritesViewModel
import com.example.animeapp.viewmodel.SeriesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PagesAdapter(
   val viewModel: SeriesViewModel,
   val favViewModel: FavoritesViewModel,
val favList:MutableList<FavoriteData>
) :
   RecyclerView.Adapter<PagesAdapter.PagesViewHolder>() {
   class PagesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
      val pageText = view.findViewById<TextView>(R.id.pageButton)


      @SuppressLint("SetTextI18n")
      fun bindData(data: PageData) {

         pageText.text = "Page:" + data.pageNumber.toString()


      }
   }

   private var pagesList: ArrayList<PageData>? = null
   fun setList(pageList: ArrayList<PageData>) {
      this.pagesList = pageList

   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagesViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.pages_items, parent, false)
      return PagesViewHolder(view)
   }

   override fun getItemCount(): Int {
      return pagesList!!.size
   }

   override fun onBindViewHolder(holder: PagesViewHolder, position: Int) {

      holder.bindData(pagesList!![position])
      val pageText = holder.view.findViewById<Button>(R.id.pageButton)

      pageText.setOnClickListener {
         CoroutineScope(Dispatchers.IO).launch {

            val job1: Deferred<Unit> = async {
               viewModel.getAllSeries(pagesList!![position].pageNumber.toString())
            }



      favViewModel.readFavorites()



            job1.await()

         }


      }

   }


}