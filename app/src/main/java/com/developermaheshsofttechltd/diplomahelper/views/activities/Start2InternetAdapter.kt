package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.views.adapter.VpnInternetAdapter

class Start2InternetAdapter : VpnInternetAdapter() {

    private lateinit var cl1: ConstraintLayout
    private lateinit var cl2: ConstraintLayout
    private lateinit var cl3: ConstraintLayout
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start2)

        tv1 = findViewById(R.id.activity_start2_tv1)
        tv2 = findViewById(R.id.activity_start2_tv2)
        tv3 = findViewById(R.id.activity_start2_tv3)
        cl1 = findViewById(R.id.activity_start2_cl1)
        cl2 = findViewById(R.id.activity_start2_cl2)
        cl3 = findViewById(R.id.activity_start2_cl3)

        cl1.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        cl2.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        cl3.setOnClickListener {
            startActivity(Intent(this, MobileNumberActivity::class.java))
        }
    }
}
