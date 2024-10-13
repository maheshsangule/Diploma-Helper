package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.databinding.ActivityProjectBinding
import com.developermaheshsofttechltd.diplomahelper.models.ProjectModel
import com.developermaheshsofttechltd.diplomahelper.models.ScreenShotModel
import com.developermaheshsofttechltd.diplomahelper.views.adapter.ProjectAdapter

class ProjectActivity : AppCompatActivity() {
    private val activity=this
    private val binding by lazy { ActivityProjectBinding.inflate(layoutInflater) }

    private val list = ArrayList<ProjectModel>()
    private val adapter = ProjectAdapter(activity, list)
    private val imageList = ArrayList<ScreenShotModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(binding.root)

        for (i in 1..8) {
            imageList.add(ScreenShotModel("https://devMahesh.excellencescholar.com/Diploma%20Helper/res/sc/$i.png"))
        }

        Log.d("VISHAY", imageList.toString())
        binding.apply {


            for (i in 1..10) {
                list.add(
                    ProjectModel(
                        R.drawable.chatgpt_img2,
                        "Diploma Helper",
                        "Welcome to My App Welcome to My App",
                        "2000",
                        "60",
                        "https://devMahesh.excellencescholar.com/Diploma%20Helper/res/raw/discount.json",
                        imageList
                    )
                )
            }

            recyclerView.adapter=adapter





        }



    }
}