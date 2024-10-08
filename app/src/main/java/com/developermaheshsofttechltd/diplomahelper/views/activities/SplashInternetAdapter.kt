package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.developermaheshsofttechltd.diplomahelper.R

class SplashInternetAdapter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.statusBarColor = getColor(R.color.gray)

        // Use a Handler to post a delayed task
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(applicationContext, HomePageActivity::class.java))
            finish()  // Optionally finish the SplashActivity to prevent going back to it
        }, 1500)
    }
}
