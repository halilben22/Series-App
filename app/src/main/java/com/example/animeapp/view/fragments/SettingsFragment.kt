package com.example.animeapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentSearchBinding
import com.example.animeapp.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

   private var _binding: FragmentSettingsBinding? = null

   private val binding get() = _binding!!

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding = FragmentSettingsBinding.inflate(inflater, container, false)
      val view = binding.root
      return view

   }


}