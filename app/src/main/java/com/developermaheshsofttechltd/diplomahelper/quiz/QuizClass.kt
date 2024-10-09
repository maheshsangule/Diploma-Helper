package com.developermaheshsofttechltd.diplomahelper.quiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.developermaheshsofttechltd.diplomahelper.constants.Base
import com.developermaheshsofttechltd.diplomahelper.constants.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizClass(private val context: Context) {
    private lateinit var sharedPreferences: SharedPreferences
    fun getQuizList(
        amount: Int,
        category: Int?,
        difficulty: String?,
        type: String?,
        categoryItemList: ArrayList<CategoryModel>
    ) {
        if (Constants.isNetworkAvailable(context)) {
            val pbDialog = Base.showProgressBar(context as Activity,true)
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            val service: QuizService = retrofit.create(QuizService::class.java)
            val dataCall: Call<QuizResponse> = service.getQuiz(amount, category, difficulty, type)

            dataCall.enqueue(object : Callback<QuizResponse> {
                override fun onResponse(
                    call: Call<QuizResponse>,
                    response: Response<QuizResponse>
                ) {
                    pbDialog.cancel()
                    if (response.isSuccessful) {
                        val responseData: QuizResponse = response.body()!!
                        val questionList = ArrayList(responseData.results)
                        if (questionList.isNotEmpty()) {
                            val intent = Intent(context, QuizQuestionActivity::class.java)
                            intent.putExtra("questionList", questionList)
                            intent.putExtra("categoryItemList", categoryItemList)
//                            intent.putExtra("image", categoryItemList)
                            context.startActivity(intent)

                            Log.d("VISHAY", "onResponse: $categoryItemList")

                        } else {
                            Base.showToast(
                                context, "We are sorry, but currently " +
                                        "we don't have $amount question for this particular selection"
                            )
                            Log.e("debug", responseData.toString())
                        }
                    } else {
                        Base.showToast(context, "Error Code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<QuizResponse>, t: Throwable) {
                    pbDialog.cancel()
                    Base.showToast(context, "CallBack Failure")
                }

            })
        } else {
//            Base.showToast(context, "Network is not Available")
//            Utils.check_internet(context as Activity)
        }
    }

    fun setRecyclerView(recycleView: RecyclerView?, isShuffle: Boolean = false) {
        if (Constants.isNetworkAvailable(context)) {
            val pbDialog = Base.showProgressBar(context as Activity,true)
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            val service: QuestionStatsService = retrofit.create(QuestionStatsService::class.java)
            val dataCall: Call<QuestionStats> = service.getData()
            dataCall.enqueue(object : Callback<QuestionStats> {
                override fun onResponse(
                    call: Call<QuestionStats>,
                    response: Response<QuestionStats>
                ) {
                    pbDialog.cancel()
                    if (response.isSuccessful) {
                        val questionStats: QuestionStats = response.body()!!
                        val categoryMap = questionStats.categories
                        val adapter = GridAdapter(

                            context,
                            Constants.getCategoryItemList(isShuffle),
                            categoryMap
                        )

                        recycleView?.adapter = adapter
                        recycleView!!.setHasFixedSize(true)
                        adapter.setOnClickListener(object : GridAdapter.OnClickListener {
                            override fun onClick(id: Int) {
                                getQuizList(
                                    15,
                                    id,
                                    null,
                                    null,
                                    Constants.getCategoryItemList(isShuffle)
                                )

                            }

                        })

                    } else {
                        Base.showToast(context, "Error Code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<QuestionStats>, t: Throwable) {
                    pbDialog.cancel()
//                    Base.showToast(context, "Network is not Available")
//                    Utils.check_internet(context as Activity)
                }

            })
        }
    }
}