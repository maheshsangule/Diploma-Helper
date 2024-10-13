package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.gray)

        binding.apply {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()  // Optionally finish the SplashActivity to prevent going back to it
            }, 1500)
        }


    }
}
