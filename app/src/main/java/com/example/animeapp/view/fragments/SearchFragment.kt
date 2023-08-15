package com.example.animeapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animeapp.databinding.FragmentSearchBinding
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.models.PageData
import com.example.animeapp.models.PopularSeriesData
import com.example.animeapp.utils.methods.SeriesAdapterMethodImpl
import com.example.animeapp.view.adapters.PagesAdapter
import com.example.animeapp.view.adapters.SeriesAdapter
import com.example.animeapp.viewmodel.FavoritesViewModel
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
   private var job1:Deferred<Unit>?=null
   private var job2:Deferred<Unit>?=null
   private var listAllSeries: ArrayList<PopularSeriesData> = arrayListOf()
   private var listSearched: ArrayList<PopularSeriesData> = arrayListOf()
   private var favList: MutableList<FavoriteData> = mutableListOf()
   private lateinit var seriesAdapter: SeriesAdapter
   val pagesList: ArrayList<PageData> = arrayListOf()
   private var seriesAdapterMethodImpl= SeriesAdapterMethodImpl()
   private lateinit var pagesAdapter: PagesAdapter
   private val viewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[SeriesViewModel::class.java]
   }

   private val favViewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[FavoritesViewModel::class.java]
   }


   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSearchBinding.inflate(inflater, container, false)
      val view = binding.root
      getDatas()
      favViewModel.readFavorites()


      pagesList.add(PageData(1))
      pagesList.add(PageData(2))
      pagesList.add(PageData(3))
      pagesList.add(PageData(4))
      pagesList.add(PageData(5))
      pagesList.add(PageData(6))
      pagesList.add(PageData(7))
      pagesList.add(PageData(8))
      pagesList.add(PageData(9))
      pagesList.add(PageData(10))

      favViewModel.favoriteObserver().observe(requireActivity()) {
         favList = it

      }

      pagesAdapter = PagesAdapter(viewModel, favViewModel, favList)
      pagesAdapter.setList(pagesList)
      binding.recyclerPages.adapter = pagesAdapter

      viewModel.observeSeries().observe(viewLifecycleOwner) {
         listAllSeries.add(it)
         seriesAdapter = SeriesAdapter(favViewModel, lifecycle,seriesAdapterMethodImpl)

         seriesAdapter.setList(listAllSeries, favList)


         binding.recyclerSeries.adapter = seriesAdapter

         binding.recyclerSeries.layoutManager =
            GridLayoutManager(activity, 2)

      }

      viewModel.observeSearchSeries().observe(viewLifecycleOwner) {
         listSearched.add(it)
         seriesAdapter = SeriesAdapter(favViewModel, lifecycle,seriesAdapterMethodImpl)
         seriesAdapter.setList(listSearched, favList)
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
            if (p0 == "") {
               getDatas()
            } else {
               CoroutineScope(Dispatchers.IO).launch {
                  job2= async {
                     favViewModel.readFavorites()
                  }
                   job1 = async {
                     viewModel.getAllSearchedSeries(p0!!.lowercase())
                  }

                  job2!!.await()

                  job1!!.await()

               }
            }



            return true
         }
      })

      return view

   }

   private fun getDatas() {
      CoroutineScope(Dispatchers.IO).launch {
         val job1: Deferred<Unit> = async {
            viewModel.getAllSeries("1")
         }

         job1.await()

      }
   }

   override fun onDestroy() {
      super.onDestroy()
      job1?.cancel()
      job2?.cancel()

   }

   override fun onDestroyView() {
      super.onDestroyView()
      job1?.cancel()
      job2?.cancel()
   }

}