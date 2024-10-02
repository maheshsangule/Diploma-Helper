package com.developermaheshsofttechltd.diplomahelper.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developermaheshsofttechltd.diplomahelper.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private val binding: ActivityQuizBinding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }

    private var categoryList = ArrayList<CategoryModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val quizClass = QuizClass(this)
        quizClass.setRecyclerView(binding.categoryRecyclerView)


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
