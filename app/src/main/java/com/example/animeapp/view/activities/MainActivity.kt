package com.example.animeapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animeapp.R
import com.example.animeapp.databinding.ActivityMainBinding
import com.example.animeapp.view.fragments.FavoritesFragment
import com.example.animeapp.view.fragments.SearchFragment
import com.example.animeapp.view.fragments.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

private lateinit var binding: ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {


      super.onCreate(savedInstanceState)


      binding = ActivityMainBinding.inflate(layoutInflater)
      val view = binding.root
      setContentView(view)

      replaceFragment(SearchFragment())
      binding.bottomNavigationView.setOnItemSelectedListener {
         when (it.itemId) {

            R.id.search_logo -> replaceFragment(SearchFragment())
            R.id.fav_logo -> replaceFragment(FavoritesFragment())
            R.id.settings_logo -> replaceFragment(SettingsFragment())


            else -> {

            }


         }
         true
      }

   }



   private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
      val fragmentManager = supportFragmentManager
      val fragmentTransaction = fragmentManager.beginTransaction()
      fragmentTransaction.replace(R.id.frame_layout, fragment)
      fragmentTransaction.commit()
   }


}