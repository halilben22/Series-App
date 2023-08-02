package com.example.animeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.animeapp.models.PopularSeriesData
import com.example.animeapp.viewmodel.SeriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   private var listAllSeries: ArrayList<PopularSeriesData> = arrayListOf()
   private var listSearched: ArrayList<PopularSeriesData> = arrayListOf()
   private val viewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[SeriesViewModel::class.java]
   }

   override fun onCreate(savedInstanceState: Bundle?) {


      super.onCreate(savedInstanceState)


      setContentView(R.layout.activity_main)

      getDatas()

      viewModel.observeSearchSeries().observe(this, {
         listSearched.add(it)

      })



      viewModel.observeSeries().observe(this) {
         listAllSeries.add(it)


      }
   }

   fun getDatas() {
      CoroutineScope(Dispatchers.IO).launch {
         val job1: Deferred<Unit> = async {
            viewModel.getAllSeries("3")

            println(listAllSeries)

         }

         val job2: Deferred<Unit> = async {
            viewModel.getAllSearchedSeries("flash")


         }

         job1.await()
         job2.await()

      }


   }

   /* private fun getDatas() {
       CoroutineScope(Dispatchers.IO).launch {
          val retrofit =
             Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()

          val aniApi = retrofit.create(AniApi::class.java)
          try {
             aniApi.getAllSeries(page = "270").enqueue(object : Callback<PopularSeriesData> {
                override fun onResponse(
                   call: Call<PopularSeriesData>,
                   response: Response<PopularSeriesData>
                ) {

                   if (response.isSuccessful) {
                      response.body()?.let { list.add(it) }
                      println(list)
                   }
                }

                override fun onFailure(call: Call<PopularSeriesData>, t: Throwable) {
                   Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }


             })
          } catch (e: Exception) {
             println(e.localizedMessage)
          }

       }

    }*/


   /*private fun getSearch() {
        CoroutineScope(Dispatchers.IO).launch {
           val retrofit =
              Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                 .build()

           val aniApi = retrofit.create(AniApi::class.java)
           try {
           aniApi.searchSeries("flash").enqueue(object :Callback<PopularSeriesData>{
              override fun onResponse(
                 call: Call<PopularSeriesData>,
                 response: Response<PopularSeriesData>
              ) {
                 if (response.isSuccessful) {
                    response.body()?.let { list2.add(it) }
                    println(list2)
                 }
              }

              override fun onFailure(call: Call<PopularSeriesData>, t: Throwable) {
                 Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
              }
           })
           } catch (e: Exception) {
              println(e.localizedMessage)
           }

       }

    }*/


}