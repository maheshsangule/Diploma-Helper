package com.developermaheshsofttechltd.diplomahelper.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.constants.Base
import com.developermaheshsofttechltd.diplomahelper.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private val binding: ActivityQuizBinding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }

    private var categoryList = ArrayList<CategoryModelClass>()
    private val activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val quizClass = QuizClass(this)
        quizClass.setRecyclerView(binding.categoryRecyclerView)


        binding.apply {
            swipeRefreshLayout.setColorScheme(R.color.colorPrimary)
            swipeRefreshLayout.setOnRefreshListener {
                val pbDialog = Base.showProgressBar(activity)
//                dialog.show()


                Handler(Looper.myLooper()!!).postDelayed({
                    swipeRefreshLayout.isRefreshing = false
                    pbDialog.cancel()
//                    quizClass.setRecyclerView(binding.categoryRecyclerView,true)
                }, 1500)
            }
        }

        // Removed unused category list setup

    }

    @SuppressLint("WrongConstant")
    override fun onStart() {
        super.onStart()

//        val rvCategoryList = binding.categoryRecyclerView
//        rvCategoryList.layoutManager = GridLayoutManager(this, 2)

        val quizClass = QuizClass(this)
        quizClass.setRecyclerView(binding.categoryRecyclerView)
    }

    companion object {
    }
}
