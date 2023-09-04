package com.example.animeapp.utils.methods

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import com.example.animeapp.R
import com.example.animeapp.models.FavoriteData
import com.example.animeapp.view.adapters.FavoritesAdapter
import com.example.animeapp.viewmodel.FavoritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteMethodsImpl : FavoriteMethods {

   override fun deleteFavorite(
      position: Int,
      holder: FavoritesAdapter.FavoritesViewHolder,
      favoritesViewModel: FavoritesViewModel,
      favList: List<FavoriteData>,
      differ: AsyncListDiffer<FavoriteData>


   ) {
      val favButton = holder.view.findViewById<ImageView>(R.id.heart_button)
      favButton.setOnClickListener {
         favButton.setImageResource(R.drawable.baseline_favorite_border_24)
         CoroutineScope(Dispatchers.IO).launch {
            delay(100)
            val job1: Deferred<Unit> = async {
               favoritesViewModel.deleteFavorite(
                  favList[position]

               )
               favoritesViewModel.readFavoritesWithDiffer(differ = differ)


            }

            job1.await()


         }


      }
   }

   override fun showCommentDialog(
      position: Int,
      holder: FavoritesAdapter.FavoritesViewHolder,
      favoritesViewModel: FavoritesViewModel,
      favList: List<FavoriteData>,
      context: Context,
      differ: AsyncListDiffer<FavoriteData>
   ) {
      holder.comment_button.setOnClickListener {
         val dialog = Dialog(context)
         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
         dialog.setContentView(R.layout.fav_bottom_sheet)
         val comment_add_btn = dialog.findViewById<Button>(R.id.comment_button)
         val comment_text = dialog.findViewById<EditText>(R.id.comment_text)

         comment_text.setText(favList[position].comment)
         dialog.show()

         dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
         )
         dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
         dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
         dialog.window!!.setGravity(Gravity.BOTTOM)

         comment_add_btn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
               favoritesViewModel.updateComment(
                  comment_text.text.toString(),
                  favList[position].id
               )
               favoritesViewModel.readFavoritesWithDiffer(differ)


               withContext(Dispatchers.Main) {
                  dialog.cancel()
                  Toast.makeText(
                     context,
                     "${favList[position].name} ile ilgili notunuz eklendi.",
                     Toast.LENGTH_SHORT
                  ).show()

               }


            }


         }
      }
   }

   override fun clearAllFavorites(
      clearButton: Button,
      favoritesViewModel: FavoritesViewModel,
      differ: AsyncListDiffer<FavoriteData>
   ) {
      clearButton.setOnClickListener {

         favoritesViewModel.deleteAllFavorites()
         favoritesViewModel.readFavoritesWithDiffer(differ)


      }
   }

   override fun showRateDialog(
      position: Int,
      holder: FavoritesAdapter.FavoritesViewHolder,
      favoritesViewModel: FavoritesViewModel,
      favList: List<FavoriteData>,
      context: Context,
      differ: AsyncListDiffer<FavoriteData>
   ) {
      holder.rate_button.setOnClickListener {
         val builder = Dialog(context)
         val inflater = LayoutInflater.from(context)
         val dialogLayout = inflater.inflate(R.layout.number_picker_dialog, null)
         val done_button = dialogLayout.findViewById<Button>(R.id.rate_done_button)
         val numberPicker = dialogLayout.findViewById<NumberPicker>(R.id.dialog_number_picker)
         numberPicker.minValue = 0
         numberPicker.maxValue = 10
         //numberPicker.textColor=Color
         with(builder) {
            setContentView(dialogLayout)
            builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()

            done_button.setOnClickListener {

               CoroutineScope(Dispatchers.IO).launch {

                  val job1: Deferred<Unit> = async {
                     favoritesViewModel.updateRate(
                        numberPicker.value.toString(),
                        favList[position].id
                     )

                  }





                  job1.await()

                  favoritesViewModel.readFavoritesWithDiffer(differ)
               }




               cancel()
            }

         }


      }
   }
}