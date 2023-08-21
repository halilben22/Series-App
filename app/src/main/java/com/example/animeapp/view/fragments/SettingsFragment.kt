package com.example.animeapp.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.animeapp.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SettingsFragment : Fragment() {

   private var _binding: FragmentSettingsBinding? = null
   private var sharedPreferences: SharedPreferences? = null


   private val binding get() = _binding!!


   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSettingsBinding.inflate(inflater, container, false)

      val view = binding.root

      val switch_btn=binding.switchBtn
      sharedPreferences = this.activity?.getSharedPreferences("MODE", Context.MODE_PRIVATE)
      val editor=   sharedPreferences!!.edit()
      val mode_theme= sharedPreferences?.getBoolean("night", false)!!
      switch_btn.isChecked = mode_theme

      switch_btn.setOnCheckedChangeListener { compoundButton, _ ->
         try {
            if (compoundButton.isChecked) {
CoroutineScope(Dispatchers.IO).launch {
   editor?.putBoolean("night", true)
   delay(100)

   withContext(Dispatchers.Main){
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
   }

}



            } else {
               CoroutineScope(Dispatchers.IO).launch {
                  editor?.putBoolean("night", false)
                  delay(100)

                  withContext(Dispatchers.Main){
                     AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                  }

               }




            }

            editor?.apply()
         } catch (e: Exception) {

            println(e.localizedMessage)
         }


      }
      return view


   }


}