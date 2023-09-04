package com.example.animeapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapp.databinding.FragmentFavoritesBinding
import com.example.animeapp.utils.methods.FavoriteMethodsImpl
import com.example.animeapp.view.adapters.FavoritesAdapter
import com.example.animeapp.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {


   private var _binding: FragmentFavoritesBinding? = null
   private lateinit var favoritesAdapter: FavoritesAdapter
   private var job1: Deferred<Unit>? = null
   private var favoriteMethodsImpl = FavoriteMethodsImpl()
   private val binding get() = _binding!!
   private val favViewModel by lazy {
      ViewModelProvider(this, defaultViewModelProviderFactory)[FavoritesViewModel::class.java]
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
      val view = binding.root

      getFavoriteDatas()
      val clearButton = binding.clearButton
      favoritesAdapter =
         FavoritesAdapter(favViewModel, clearButton, requireActivity(), favoriteMethodsImpl)


      favViewModel.favoriteObserver().observe(requireActivity()) {
         favoritesAdapter.differ.submitList(it)
         makeFavAdapter()
      }
      return view
   }

   private fun getFavoriteDatas() {
      CoroutineScope(Dispatchers.IO).launch {

         job1 = async {
            favViewModel.readFavorites()
         }
         job1!!.await()


      }

   }

   private fun makeFavAdapter() {

      binding.recyclerFavs.adapter = favoritesAdapter
      binding.recyclerFavs.setHasFixedSize(true)
      binding.recyclerFavs.layoutManager =
         LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
   }

   override fun onDestroy() {
      super.onDestroy()
      job1?.cancel()
   }

   override fun onDestroyView() {
      super.onDestroyView()
      job1?.cancel()

   }


}