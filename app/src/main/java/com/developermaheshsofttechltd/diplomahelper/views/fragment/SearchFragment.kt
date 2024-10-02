package com.developermaheshsofttechltd.diplomahelper.views.fragment

import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.developermaheshsofttechltd.diplomahelper.R

class SearchFragment : Fragment() {

    private lateinit var dialog: Dialog
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Initialize and configure the dialog
        dialog = Dialog(requireContext()).apply {
            requestWindowFeature(Dialog.BUTTON_POSITIVE)
            setContentView(R.layout.progressbar)
            setCanceledOnTouchOutside(false)
        }

        // Initialize SwipeRefreshLayout and set its listener
        swipeRefreshLayout = view.findViewById(R.id.fragment_search_swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener {
            object : CountDownTimer(1000, 2000) {
                override fun onTick(millisUntilFinished: Long) {
                    dialog.show()
                }

                override fun onFinish() {
                    dialog.dismiss()
                    swipeRefreshLayout.isRefreshing = false
                }
            }.start()
        }

        return view
    }
}
