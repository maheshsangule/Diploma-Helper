package com.developermaheshsofttechltd.diplomahelper.constants

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.databinding.LayoutToastBinding
import com.developermaheshsofttechltd.diplomahelper.quiz.CategoryModel

object Constants {

//    val difficultyList = listOf("Any", "Easy", "Medium", "Hard")
//    val typeList = listOf("Any", "Multiple Choice", "True/false")
//    const val user = "USER"
//    const val allTimeScore = "allTimeScore"
//    const val weeklyScore = "weeklyScore"
//    const val monthlyScore = "monthlyScore"
//    const val lastGameScore = "lastGameScore"


    @SuppressLint("NewApi")
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    fun getCategoryItemList(isShuffle:Boolean=false): ArrayList<CategoryModel> {
        val list = ArrayList<CategoryModel>()

        list.add(
            CategoryModel("9", R.drawable.general_knowledge, "General Knowledge")
        )
        list.add(
            CategoryModel("31", R.drawable.anime, "Anime")
        )

        list.add(
            CategoryModel("32", R.drawable.cartoon, "Cartoons & Animations")
        )
        list.add(
            CategoryModel("24", R.drawable.politics, "Politics")
        )
        list.add(
            CategoryModel("10", R.drawable.books, "Books")
        )
        list.add(
            CategoryModel("11", R.drawable.movies, "Film")
        )
        list.add(
            CategoryModel("12", R.drawable.music, "Music")
        )

        list.add(
            CategoryModel("14", R.drawable.television, "Television")
        )
        list.add(
            CategoryModel("15", R.drawable.video_games, "Video Games")
        )
        list.add(
            CategoryModel("16", R.drawable.board_game, "Board Games")
        )
        list.add(
            CategoryModel("17", R.drawable.science, "Science & Nature")
        )
        list.add(
            CategoryModel("18", R.drawable.computer, "Computer")
        )
        list.add(
            CategoryModel("19", R.drawable.maths, "Mathematics")
        )
        list.add(
            CategoryModel("20", R.drawable.mythology, "Mythology")
        )
        list.add(
            CategoryModel("21", R.drawable.sport, "Sports")
        )
        list.add(
            CategoryModel("22", R.drawable.geography, "Geography")
        )
        list.add(
            CategoryModel("13", R.drawable.musicals, "Musicals & Theatres")
        )
        list.add(
            CategoryModel("23", R.drawable.history1, "History")
        )

        list.add(
            CategoryModel("25", R.drawable.art, "Art")
        )
        list.add(
            CategoryModel("26", R.drawable.celebrity, "Celebrities")
        )
        list.add(
            CategoryModel("27", R.drawable.animals, "Animals")
        )
        list.add(
            CategoryModel("28", R.drawable.vehicles, "Vehicles")
        )
        list.add(
            CategoryModel("29", R.drawable.comic, "Comics")
        )
        list.add(
            CategoryModel("30", R.drawable.gadgets, "Gadgets")
        )

        return if (isShuffle) {
            list.shuffle()
            list
        } else {
            list
        }

    }

    fun getRandomOptions(
        correctAnswer: String,
        incorrectAnswer: List<String>
    ): Pair<String, List<String>> {
        val list = mutableListOf<String>()
        val decodedCorrectAnswer = decodeHtmlString(correctAnswer)
        list.add(decodedCorrectAnswer)
        for (i in incorrectAnswer) {
            list.add(decodeHtmlString(i))
        }
        list.shuffle()
        return Pair(decodedCorrectAnswer, list)
    }

    fun decodeHtmlString(htmlEncoded: String): String {
        return HtmlCompat.fromHtml(htmlEncoded, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }

    fun getCategoryStringArray(): List<String> {
        val list = getCategoryItemList()
        val result = mutableListOf<String>()
        result.add("Any")
        for (i in list)
            result.add(i.name)
        return result
    }

    fun showToast(
        context: Activity,
        binding: LayoutToastBinding,
        tvMessage: String = "Something went wrong",
        color: Int = R.color.red,
        isShowToast: Boolean = true
    ) {

        if(isShowToast)
        {
            binding.layoutConstraint.visibility = View.VISIBLE
            binding.tvMessage.text = tvMessage
            binding.layoutConstraint.backgroundTintList =
                ContextCompat.getColorStateList(context, color)

            Handler(Looper.myLooper()!!).postDelayed(
                {
                    binding.layoutConstraint.visibility = View.GONE
                    binding.tvMessage.text = tvMessage
                }, 3000
            )
        }
        else
        {
            binding.layoutConstraint.visibility = View.GONE
            binding.tvMessage.text = tvMessage
        }



    }
}