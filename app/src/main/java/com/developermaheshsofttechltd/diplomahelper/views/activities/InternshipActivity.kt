package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.developermaheshsofttechltd.diplomahelper.R

@Suppress("DEPRECATION")
class InternshipActivity : AppCompatActivity() {
    private lateinit var intrnAsomBton: Button
    private lateinit var listEnterAsom: Button
    private lateinit var topGovtSector: Button
    private lateinit var adView: LinearLayout
    private val urls: Array<String> = arrayOf(
        "https://polytechnicdiplomapp.blogspot.com/p/top-psu-for-internship.html",
        "file:///android_asset/sector.html",
        "https://drive.google.com/file/d/1wzP9NfxwOBOE3KYKouiuLBjxwAfUgBhY/preview"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internship)

        val toolbar = findViewById<Toolbar>(R.id.app_bar)
        toolbar.setNavigationOnClickListener { this.onBackPressed() }

        topGovtSector = findViewById(R.id.TopGovtSector)
        intrnAsomBton = findViewById(R.id.IntrnAsomBton)
        listEnterAsom = findViewById(R.id.ListEnterAsom)

        topGovtSector.setOnClickListener {
            Toast.makeText(this, "Under Maintenance", Toast.LENGTH_SHORT).show()
        }
        intrnAsomBton.setOnClickListener {
            Toast.makeText(this, "Under Maintenance", Toast.LENGTH_SHORT).show()
        }
        listEnterAsom.setOnClickListener {
            Toast.makeText(this, "Under Maintenance", Toast.LENGTH_SHORT).show()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
