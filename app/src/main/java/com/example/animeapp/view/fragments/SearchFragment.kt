package com.example.animeapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animeapp.databinding.FragmentSearchBinding
import com.example.animeapp.models.PopularSeriesData
import com.example.animeapp.view.adapters.SeriesAdapter
import com.example.animeapp.viewmodel.SeriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

   private var _binding: FragmentSearchBinding? = null

   private val binding get() = _binding!!

   private var listAllSeries: ArrayList<PopularSeriesData> = arrayListOf()
   private var listSearched: ArrayList<PopularSeriesData> = arrayListOf()
   private lateinit var seriesAdapter: SeriesAdapter
   private val viewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[SeriesViewModel::class.java]
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSearchBinding.inflate(inflater, container, false)
      val view = binding.root

      getDatas()


      viewModel.observeSeries().observe(viewLifecycleOwner) {
         listAllSeries.add(it)
         seriesAdapter = SeriesAdapter()
         seriesAdapter.setList(listAllSeries)
         binding.recyclerSeries.adapter = seriesAdapter
         binding.recyclerSeries.layoutManager =
            GridLayoutManager(activity, 2)

      }



      binding.searchBar.setOnQueryTextListener(object :
         androidx.appcompat.widget.SearchView.OnQueryTextListener {
         override fun onQueryTextSubmit(p0: String?): Boolean {
            return false
         }

         override fun onQueryTextChange(p0: String?): Boolean {
            if (p0 == ""){
               getDatas()
            }
            else {
               CoroutineScope(Dispatchers.IO).launch {
                  val job1: Deferred<Unit> = async {


                     viewModel.getAllSearchedSeries(p0!!.lowercase())


                  }

                  job1.await()

               }
            }
            viewModel.observeSearchSeries().observe(viewLifecycleOwner) {
               listSearched.add(it)
               seriesAdapter = SeriesAdapter()
               seriesAdapter.setList(listSearched)
               binding.recyclerSeries.adapter = seriesAdapter
               binding.recyclerSeries.layoutManager =
                  GridLayoutManager(activity, 2)
            }


            return true
         }

      })


      return view


   }






   private fun getDatas() {
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


}