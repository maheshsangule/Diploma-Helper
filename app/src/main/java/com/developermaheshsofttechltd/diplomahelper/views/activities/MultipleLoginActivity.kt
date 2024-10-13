package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.content.Intent
import android.os.Bundle
import com.developermaheshsofttechltd.diplomahelper.databinding.ActivityMultipleLoginBinding
import com.developermaheshsofttechltd.diplomahelper.views.adapter.VpnInternetAdapter

class MultipleLoginActivity : VpnInternetAdapter() {

//    private lateinit var cl1: ConstraintLayout
//    private lateinit var cl2: ConstraintLayout
//    private lateinit var cl3: ConstraintLayout
//    private lateinit var tv1: TextView
//    private lateinit var tv2: TextView
//    private lateinit var tv3: TextView

    private val binding by lazy { ActivityMultipleLoginBinding.inflate(layoutInflater) }
    private val activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        tv1 = findViewById(R.id.activity_start2_tv1)
//        tv2 = findViewById(R.id.activity_start2_tv2)
//        tv3 = findViewById(R.id.activity_start2_tv3)
//        cl1 = findViewById(R.id.activity_start2_cl1)
//        cl2 = findViewById(R.id.activity_start2_cl2)
//        cl3 = findViewById(R.id.activity_start2_cl3)

        binding.apply {
            tvLogin.setOnClickListener {
                startActivity(Intent(activity, LoginActivity::class.java))
            }
            tvSignup.setOnClickListener {
                startActivity(Intent(activity, SignupActivity::class.java))
            }
            tvOtpLogin.setOnClickListener {
                startActivity(Intent(activity, MobileNumberActivity::class.java))
            }
        }


    }
}
