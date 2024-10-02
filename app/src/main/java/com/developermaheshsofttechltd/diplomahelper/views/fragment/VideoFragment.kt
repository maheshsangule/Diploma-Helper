package com.developermaheshsofttechltd.diplomahelper.views.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.developermaheshsofttechltd.diplomahelper.R

class VideoFragment : Fragment() {

    private lateinit var dialog: Dialog
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var webview: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)

        // Initialize WebView and SwipeRefreshLayout
        webview = view.findViewById(R.id.fragment_video_webview)
        swipeRefreshLayout = view.findViewById(R.id.fragment_video_swipe_refresh_layout)

        // Initialize and configure the dialog
        dialog = Dialog(requireContext()).apply {
//            requestWindowFeature(Dialog.FEATURE_NO_TITLE)
            setContentView(R.layout.progressbar)
            setCanceledOnTouchOutside(false)
        }

        // Load data into WebView
        loadData()

        // Set up SwipeRefreshLayout listener
        swipeRefreshLayout.setOnRefreshListener {
            object : CountDownTimer(1000, 2000) {
                override fun onTick(millisUntilFinished: Long) {
                    dialog.show()
                }

                override fun onFinish() {
                    dialog.dismiss()
                    swipeRefreshLayout.isRefreshing = false
                    loadData()
                }
            }.start()
        }

        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadData() {
        webview.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl("https://econtent.msbte.ac.in/econtent/econtent_home.php")
        }
    }
}
