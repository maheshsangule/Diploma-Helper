package com.developermaheshsofttechltd.diplomahelper.quiz

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.databinding.QuizResultBinding
import com.developermaheshsofttechltd.diplomahelper.views.activities.Utils


class QuizResultActivity : AppCompatActivity() {
    //    var currentChance = 0L
    private val binding: QuizResultBinding by lazy {
        QuizResultBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val totalQuestion = intent.getStringExtra("totalquestion")!!.toInt() / 2
        binding.btnRetakeQuiz.setOnClickListener {
            finish()
        }

        binding.btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Earning Quiz mein jeet hasil ki hai, ab teri baari hai! \uD83D\uDCA1 Apne knowledge ka fayda utha aur Download kar Earning Quiz!\nMuze to reward bhi mil gaya ab teri bari he bhai!\n\nDownload link- https://play.google.com/store/apps/details?id=" + packageName
            )

            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                ex.localizedMessage?.let { it1 -> Utils.customToast(this, it1.toString()) }
            }
        }


        if (intent.getStringExtra("score")?.toInt()!! > totalQuestion) {
            if (intent.getStringExtra("score")?.toInt()!! > totalQuestion - 1
            ) {
                binding.tvScore.text = intent.getStringExtra("score")
            } else {
                binding.tvScore.text = "0" + intent.getStringExtra("score")
            }
//            Firebase.database.reference.child("PlayChance")
//                .child(Firebase.auth.currentUser!!.uid)
//                .setValue(currentChance + 1)
            binding.lavGreetingJson.visibility = View.VISIBLE
            binding.tvToatlQuestion.text = "/" + intent.getStringExtra("totalquestion")
            binding.ivImg.setImageResource(R.drawable.winner_icon2)
            binding.tvGreetings.text = "Congratulations"
            binding.tvSubGreetings.text = "You have won 1 chance to \nspin the wheel"

        } else {
            if (intent.getStringExtra("score")?.toInt()!! > intent.getStringExtra("totalquestion")!!
                    .toInt() - 1
            ) {
                binding.tvScore.text = intent.getStringExtra("score")
            } else {
                binding.tvScore.text = "0" + intent.getStringExtra("score")
            }
            binding.tvToatlQuestion.text = "/" + intent.getStringExtra("totalquestion").toString()
            binding.lavGreetingJson.visibility = View.GONE
            binding.tvGreetings.text = "Hard Luck"
            binding.tvSubGreetings.text = "Better luck next time"
            binding.ivImg.setImageResource(R.drawable.sorry_icon)


        }
//        Glide.with(this)
//            .load(R.drawable.celebration)
//            .asGif()
//            .into(binding.llCelebration);
    }
}