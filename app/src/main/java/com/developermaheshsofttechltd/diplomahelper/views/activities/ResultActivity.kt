package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import com.developermaheshsofttechltd.diplomahelper.R

class ResultActivity : AppCompatActivity() {

    companion object {
        lateinit var captionInput: EditText
        var data: String = ""
        lateinit var dialog2: Dialog
        var enrollment: String = ""
        lateinit var resultView: WebView
        lateinit var search: Button
        var urlEnroll: String = ""
    }

    private lateinit var about: ImageView
    private lateinit var aboutDialog: Dialog
    private var caption: String? = null
    private lateinit var checkBox: AppCompatCheckBox
    private lateinit var enrollmentNo: EditText
    private var fetchUnderstood: Int = 0
    private val handler = Handler()
    private lateinit var iUnderstoodDialog: Dialog
    private lateinit var runnable: Runnable
    private lateinit var runnable1: Runnable
    private lateinit var runnable2: Runnable
    private lateinit var runnable3: Runnable
    private lateinit var serverDialog: Dialog
    private lateinit var view2: View
    private lateinit var webView2: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()
    }
}
