package com.developermaheshsofttechltd.diplomahelper.branches

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.views.activities.BranchesResultActivity

class COBranch : AppCompatActivity() {
    private lateinit var branchesName: TextView
    private lateinit var imageSlider: ImageSlider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coactivity)

        branchesName = findViewById(R.id.activity_co_tv_action_bar)
        branchesName.text = intent.getStringExtra("branch_name") ?: ""

        supportActionBar?.hide()

        imageSlider = findViewById(R.id.activity_co_image_slider)

        val slideModels = listOf(
            SlideModel(R.drawable.chatgpt_img2, ScaleTypes.FIT),
            SlideModel(R.drawable.it_slider, ScaleTypes.FIT),
            SlideModel(R.drawable.electrical_slider, ScaleTypes.FIT),
            SlideModel(R.drawable.electronices_slider, ScaleTypes.FIT),
            SlideModel(R.drawable.chatgpt_img2, ScaleTypes.FIT),
            SlideModel(R.drawable.chatgpt_img2, ScaleTypes.FIT)
        )

        imageSlider.setImageList(slideModels)
    }

//    fun branchesArrow(view: View) {
//        startActivity(Intent(this, BranchesResultActivity::class.java))
//    }
}
