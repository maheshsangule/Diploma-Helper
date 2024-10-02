package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developermaheshsofttechltd.diplomahelper.R

class GetAllUserDetailsActivity : AppCompatActivity() {
    /* access modifiers changed from: protected */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_all_user_details)
        supportActionBar!!.hide()
    }
}
