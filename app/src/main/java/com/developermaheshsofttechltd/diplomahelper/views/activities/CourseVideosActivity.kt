package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.developermaheshsofttechltd.diplomahelper.databinding.ActivityCourseVideosBinding

class CourseVideosActivity : AppCompatActivity() {


    private val binding by lazy { ActivityCourseVideosBinding.inflate(layoutInflater) }

    //    private lateinit var dialog: Dialog
    private val activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        supportActionBar?.hide()
//        showProgressBar(activity, true)
        loadData()
        binding.apply {

//            appbarlayout.toolBar.setBackgroundColor(Color.TRANSPARENT)
//            appbarlayout.appBarLayout.setBackgroundColor(Color.TRANSPARENT)
//            appbarlayout.toolBar.setNavigationIconTint(Color.BLACK)

            appbarlayout.toolBar.title = "Courses Videos"


            swipeRefreshLayout.setOnRefreshListener {

                Handler(Looper.myLooper()!!).postDelayed(
                    {
                        swipeRefreshLayout.isRefreshing = false
                        loadData()
                    }, 1500
                )
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
//    private fun loadData() {
//        binding.apply {
//            webviewCourses.apply {
//                showProgressBar(activity, false)
//                settings.javaScriptEnabled = true
//                webViewClient = WebViewClient()
//                loadUrl("https://econtent.msbte.ac.in/econtent/econtent_home.php")
//            }
//        }
//
//    }

    private fun loadData() {
        binding.apply {
            webviewCourses.apply {
                // Enable JavaScript
                settings.javaScriptEnabled = true

                // Set WebViewClient to handle page navigation
                webViewClient = WebViewClient()

                // Handle file downloads
                setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
                    val request = DownloadManager.Request(Uri.parse(url))
                    request.setMimeType(mimetype)
                    request.addRequestHeader("User-Agent", userAgent)
                    request.setDescription("Downloading file...")
                    request.setTitle(Uri.parse(url).lastPathSegment)

                    // Notify user about download start
                    Toast.makeText(context, "Downloading file...", Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, Uri.parse(url).lastPathSegment, Toast.LENGTH_SHORT).show()

                    // Allow downloads only on Wi-Fi
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    request.setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS,
                        Uri.parse(url).lastPathSegment
                    )

                    val downloadManager =
                        context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                    downloadManager.enqueue(request)
                }

                // Load the initial URL
                loadUrl("https://econtent.msbte.ac.in/econtent/econtent_home.php")
            }
        }
    }


    override fun onBackPressed() {
        binding.webviewCourses.apply {
            // If the WebView can navigate back, go back within the WebView
            if (canGoBack()) {
                goBack()
            } else {
                // Otherwise, exit the activity
                super.onBackPressed()
            }
        }
    }
}
