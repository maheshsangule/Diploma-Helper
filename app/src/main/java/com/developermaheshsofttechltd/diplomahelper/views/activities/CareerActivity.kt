package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.developermaheshsofttechltd.diplomahelper.R

class CareerActivity : AppCompatActivity() {

    private val urls = arrayOf(
        "https://www.collegedekho.com/articles/best-career-options-after-polytechnic/",
        "https://www.coursesxpert.com/best-diploma-courses-after-graduation-in-india/#:~:text=List%20of%20Diploma%20Courses%20after%20Graduation%20%20,%20Postgraduate%20Diploma%20%2015%20more%20rows%20",
        "https://blog.hubspot.com/sales/small-business-ideas",
        "https://dpe.assam.gov.in/portlets/state-psu-awards"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_career)

        findViewById<Toolbar>(R.id.app_bar).setNavigationOnClickListener { onBackPressed() }

        setButtonClickListener(R.id.jobopobotn, urls[0])
        setButtonClickListener(R.id.govtjobbotn, urls[1])
        setButtonClickListener(R.id.ListJobOptn, urls[2])
        setButtonClickListener(R.id.Assam_psu, urls[3])

        findViewById<Button>(R.id.prijobbotn).setOnClickListener {
            startActivity(Intent(this, JobOppurtinatyActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.more_job_info).setOnClickListener {
            Toast.makeText(this, "Under Maintenance", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setButtonClickListener(buttonId: Int, url: String) {
        findViewById<Button>(buttonId).setOnClickListener {
            val intent = Intent(this, JobOppurtinatyActivity::class.java)
            intent.putExtra("linkValue", url)
            startActivity(intent)
            finish()
        }
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
