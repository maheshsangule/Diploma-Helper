package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.databinding.ActivityProjectBinding

class ProjectActivity : AppCompatActivity() {
    private val activity=this
    private val binding by lazy { ActivityProjectBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.apply {

        }



    }
}