package com.developermaheshsofttechltd.diplomahelper.quiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.constants.Constants
import com.developermaheshsofttechltd.diplomahelper.databinding.ActivityQuestionQuizBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class QuizQuestionActivity : AppCompatActivity() {
    private var binding: ActivityQuestionQuizBinding? = null
    private lateinit var questionList: ArrayList<QuizResult>
    private var position = 0
    private var allowPlaying = true
    var isNext=false
    private val resultList = ArrayList<ResultModel>()
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionQuizBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE)

//        Glide.with(this).load(sharedPreferences.getString("imageUrl", "")).into(binding!!.profilePic);
        binding!!.tvCatTitle.text = sharedPreferences.getString("title", "").toString()
        binding!!.categoryImageQuiz.setImageResource(sharedPreferences.getInt("image",0).toInt())

//        val pref =getSharedPreferences("userCoin", Context.MODE_PRIVATE)

//        binding!!.moneyWithdrawal.setText(pref.getString("coin","").toString())
//        binding!!.tvName.text = sharedPreferences.getString("name", "").toString()

        questionList = intent.getSerializableExtra("questionList") as ArrayList<QuizResult>
//        binding?.pbProgress?.max = questionList.size
        setQuestion()
        setOptions()
//        binding?.tvProgress?.text = "1/${questionList.size}"
        binding?.btnNext?.setOnClickListener {
            if(isNext)
            {
                onNext()
                isNext=false
            }
            else
            {
                Toast.makeText(this,"Please select option",Toast.LENGTH_SHORT).show()
            }

        }
        val redBg = ContextCompat.getDrawable(this, R.drawable.red_button_bg)
        val optionClickListener = OnClickListener { view ->
            if (allowPlaying) {
                 isNext = true
                view.background = redBg
                showCorrectAnswer()
                setScore(view as Button?)
                allowPlaying = false
            }
        }

        binding?.option1?.setOnClickListener(optionClickListener)
        binding?.option2?.setOnClickListener(optionClickListener)
        binding?.option3?.setOnClickListener(optionClickListener)
        binding?.option4?.setOnClickListener(optionClickListener)

    }

    private fun onNext() {
        val resultModel = ResultModel(

            questionList[position].type,
            questionList[position].difficulty,
            score
        )
        resultList.add(resultModel)
        if (position < questionList.size - 1) {
            position++
            setQuestion()
            setOptions()
//            binding?.pbProgress?.progress = position + 1
//            binding?.tvProgress?.text = "${position + 1}/${questionList.size}"
            resetButtonBackground()
            allowPlaying = true
        } else {
            endGame()
        }
    }

    var count = 0
    private fun setQuestion() {

        count++
        if (count == 15) {
            binding!!.btnNext.setText("Submit")
        }
        if (count < 10) {
            binding!!.tvCurrentQuestion.setText("0"+count.toString())
        } else {
            binding!!.tvCurrentQuestion.setText(count.toString())
        }
        val decodedQuestion = Constants.decodeHtmlString(questionList[position].question)
        binding?.tvQuestion?.text = decodedQuestion
    }

    private lateinit var correctAnswer: String
    private lateinit var optionList: List<String>
    private fun setOptions() {
        val question = questionList[position]
        val temp = Constants.getRandomOptions(question.correct_answer, question.incorrect_answers)
        optionList = temp.second
        correctAnswer = temp.first
        binding?.option1?.text = optionList[0]
        binding?.option2?.text = optionList[1]
        if (question.type == "multiple") {
            binding?.option3?.visibility = View.VISIBLE
            binding?.option4?.visibility = View.VISIBLE
            binding?.option3?.text = optionList[2]
            binding?.option4?.text = optionList[3]
        } else {
            binding?.option3?.visibility = View.GONE
            binding?.option4?.visibility = View.GONE
        }
    }

    private fun setScore(button: Button?) {
        if (correctAnswer == button?.text)
            score++;
    }

    private fun showCorrectAnswer() {
        val blueBg = ContextCompat.getDrawable(this, R.drawable.blue_button_bg)
        when (true) {
            (correctAnswer == optionList[0]) -> binding?.option1?.background = blueBg
            (correctAnswer == optionList[1]) -> binding?.option2?.background = blueBg
            (correctAnswer == optionList[2]) -> binding?.option3?.background = blueBg
            else -> binding?.option4?.background = blueBg
        }
    }

    private fun resetButtonBackground() {
        val grayBg = ContextCompat.getDrawable(this, R.drawable.gray_button_bg)
        binding?.option1?.background = grayBg
        binding?.option2?.background = grayBg
        binding?.option3?.background = grayBg
        binding?.option4?.background = grayBg
    }


    private fun endGame() {
        val intent = Intent(this, QuizResultActivity::class.java)
        intent.putExtra("score", score.toString())
        intent.putExtra("totalquestion", questionList.size.toString())
        startActivity(intent)
        finish()
    }
    val callback = this.onBackPressedDispatcher.addCallback(this) {
        quitQuiz(this@QuizQuestionActivity, "")

    }


    private fun quitQuiz(context: Activity, s: String) {
        val sheetDialog: BottomSheetDialog?
        sheetDialog = BottomSheetDialog(context, R.style.BottomSheetStyle)
        val inflater: LayoutInflater = context.getLayoutInflater()
        val view: View = inflater.inflate(
            R.layout.quiz_onback,
            context.findViewById<View>(R.id.quiz_onback) as LinearLayout?
        )

        val btn_quit = view.findViewById<AppCompatButton>(R.id.btn_quit)
        val btn_continue = view.findViewById<AppCompatButton>(R.id.btn_continue)
        val tv_text = view.findViewById<TextView>(R.id.tv_text)

        if (s == "home") {
            tv_text.setText("Are you sure want to Exit App?")
            btn_quit.setText("Yes")
            btn_continue.setText("No")
            btn_quit.setOnClickListener {
                context.finish()
            }
            btn_continue.setOnClickListener {
                sheetDialog.dismiss()
            }
        } else {
            tv_text.setText("Are you sure want to Quit quiz?")
            btn_quit.setText("Quit")
            btn_continue.setText("Continue")
            btn_quit.setOnClickListener {
                context.finish()
            }
            btn_continue.setOnClickListener {
                sheetDialog.dismiss()
            }
        }

        sheetDialog.setContentView(view)
        sheetDialog.show()

    }
}