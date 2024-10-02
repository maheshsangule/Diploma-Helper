package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.developermaheshsofttechltd.diplomahelper.R

@Suppress("DEPRECATION")
class JobOppurtinatyActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_oppurtinaty)

        val toolbar: Toolbar = findViewById(R.id.app_bar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        webView = findViewById(R.id.CareerWebview)
        webView.settings.apply {
            loadsImagesAutomatically = true
            javaScriptEnabled = true
            domStorageEnabled = true
        }
        webView.webViewClient = object : WebViewClient() {
            private val progressDialog = ProgressDialog(this@JobOppurtinatyActivity).apply {
                setMessage("Loading...!")
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressDialog.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressDialog.dismiss()
            }
        }
        webView.webChromeClient = WebChromeClient()

        val url = intent.getStringExtra("linkValue")
        url?.let {
            webView.loadUrl(it)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, CareerActivity::class.java))
        finish()
    }
}
