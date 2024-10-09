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
import com.developermaheshsofttechltd.diplomahelper.databinding.FragmentResultBinding

class ResultFragment : Fragment() {


//    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
//    private lateinit var webview: WebView

    private val binding by lazy { FragmentResultBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.fragment_result, container, false)

        // Initialize WebView and SwipeRefreshLayout
//        webview = view.findViewById(R.id.fragment_video_webview)
//        swipeRefreshLayout = view.findViewById(R.id.fragment_video_swipe_refresh_layout)

        // Initialize and configure the dialog


        // Load data into WebView
//        loadData()

        // Set up SwipeRefreshLayout listener
        binding.apply {


        }


        return binding.root
    }


}
