package com.example.animeapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapp.databinding.FragmentFavoritesBinding
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.models.PopularSeriesData
import com.example.animeapp.view.adapters.FavoritesAdapter
import com.example.animeapp.viewmodel.FavoritesViewModel
import com.example.animeapp.viewmodel.SeriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {


   private var _binding: FragmentFavoritesBinding? = null
   private var listAllSeries: ArrayList<PopularSeriesData> = arrayListOf()
   private var favList: MutableList<FavoriteData> = mutableListOf()
   private lateinit var favoritesAdapter: FavoritesAdapter
   private val binding get() = _binding!!
   private val favViewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[FavoritesViewModel::class.java]
   }
   private val viewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[SeriesViewModel::class.java]
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
      val view = binding.root
      getFavoriteDatas()
      val clearButton = binding.clearButton
      favoritesAdapter = FavoritesAdapter(favViewModel, clearButton,activity!!)


      favViewModel.favoriteObserver().observe(requireActivity()) {
         favList = it
         makeFavAdapter()
      }
      return view
   }

   private fun getFavoriteDatas() {
      CoroutineScope(Dispatchers.IO).launch {

         val job1: Deferred<Unit> = async {
            favViewModel.readFavorites()
         }
         job1.await()


      }

   }

   private fun makeFavAdapter() {
      favoritesAdapter.setList(favList)
      binding.recyclerFavs.adapter = favoritesAdapter
      binding.recyclerFavs.layoutManager =
         LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
   }


}