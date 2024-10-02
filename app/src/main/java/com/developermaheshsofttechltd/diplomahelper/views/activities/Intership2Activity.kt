package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.developermaheshsofttechltd.diplomahelper.R

@Suppress("DEPRECATION")
class Intership2Activity : AppCompatActivity() {

    private lateinit var webView2: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internship)

        // Initialize WebView if needed (e.g., load a URL or set up settings)
//        webView2 = findViewById(R.id.webView2) // Assuming you have a WebView with this ID in your layout
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        // Call super class method to handle the back press
        super.onBackPressed()
        // Finish the activity
        finish()
    }
}
