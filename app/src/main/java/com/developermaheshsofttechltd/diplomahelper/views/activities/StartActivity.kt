package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.content.Intent
import android.os.Bundle
import com.developermaheshsofttechltd.diplomahelper.databinding.ActivityStartBinding
import com.developermaheshsofttechltd.diplomahelper.views.adapter.VpnInternetAdapter

class StartActivity : VpnInternetAdapter() {

    private val binding by lazy { ActivityStartBinding.inflate(layoutInflater) }
    private val activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {

            ivArrow.setOnClickListener {
                startActivity(Intent(activity, MultipleLoginActivity::class.java))
                finish()
            }
        }
    }
}
