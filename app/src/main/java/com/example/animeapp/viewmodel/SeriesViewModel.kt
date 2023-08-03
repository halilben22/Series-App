package com.example.animeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animeapp.models.PopularSeriesData
import com.example.animeapp.repository.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SeriesViewModel @Inject constructor(private val repository: SeriesRepository) : ViewModel() {

   private val allSeriesLiveData: MutableLiveData<PopularSeriesData> = MutableLiveData()
   private val searchSeriesLiveData: MutableLiveData<PopularSeriesData> = MutableLiveData()
  var isProgressBarVisible: Boolean = false

   fun observeSeries(): MutableLiveData<PopularSeriesData> {

      return allSeriesLiveData
   }


   fun observeSearchSeries(): MutableLiveData<PopularSeriesData> {

      return searchSeriesLiveData
   }

   fun getAllSeries(page: String) {

      repository.getAllSeries(page, allSeriesLiveData)

   }


   fun getAllSearchedSeries(query: String) {

      repository.getSearchSeries(query, searchSeriesLiveData)

   }

}