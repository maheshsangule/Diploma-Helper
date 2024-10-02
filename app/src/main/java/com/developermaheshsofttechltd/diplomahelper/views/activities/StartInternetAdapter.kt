package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.views.adapter.VpnInternetAdapter

class StartInternetAdapter : VpnInternetAdapter() {
    private lateinit var ivArrow: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        ivArrow = findViewById(R.id.iv_arrow)
        ivArrow.setOnClickListener {
            startActivity(Intent(this, Start2InternetAdapter::class.java))
            finish()
        }
    }
}
