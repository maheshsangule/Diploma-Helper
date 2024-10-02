package com.developermaheshsofttechltd.diplomahelper.views.adapter

import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.views.activities.Utils
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.net.NetworkInterface
import java.util.*

open class VpnInternetAdapter : AppCompatActivity() {

    private lateinit var dialog: Dialog
    private lateinit var sheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDialogs()
        checkVpn()
        checkInternet()
    }

    private fun setupDialogs() {
        sheetDialog = BottomSheetDialog(this, R.style.BottomSheetStyle)
        dialog = Dialog(this).apply {
            requestWindowFeature(1)
            setContentView(R.layout.progressbar)
            setCanceledOnTouchOutside(false)
        }
    }

    private fun checkInternet() {
        if (!Utils.isConnected(this)) {
            val view = layoutInflater.inflate(
                R.layout.no_internet_2,
                findViewById<LinearLayout>(R.id.bottomsheet_dialog_confirm_logout)!!,
                false
            )
            sheetDialog.setContentView(view)
            sheetDialog.setCancelable(false)
            sheetDialog.show()

            view.findViewById<Button>(R.id.btn_no_internet2).setOnClickListener {
                object : CountDownTimer(1000, 2000) {
                    override fun onTick(millisUntilFinished: Long) {
                        dialog.show()
                        sheetDialog.dismiss()
                    }

                    override fun onFinish() {
                        dialog.dismiss()
                        checkInternet()
                    }
                }.start()
            }
        }
    }

    private fun checkVpn() {
        if (isVpn()) {
            val view = layoutInflater.inflate(
                R.layout.no_internet_2,
                findViewById<LinearLayout>(R.id.bottomsheet_dialog_confirm_logout)!!,
                false
            )
            view.findViewById<TextView>(R.id.tv1).text = "Please turn off the VPN to use all"
            view.findViewById<TextView>(R.id.tv2).text = "features of the application"

            sheetDialog.setContentView(view)
            sheetDialog.setCancelable(false)
            sheetDialog.show()

            view.findViewById<Button>(R.id.btn_no_internet2).setOnClickListener {
                object : CountDownTimer(1000, 2000) {
                    override fun onTick(millisUntilFinished: Long) {
                        dialog.show()
                        sheetDialog.dismiss()
                    }

                    override fun onFinish() {
                        dialog.dismiss()
                        checkVpn()
                    }
                }.start()
            }
        }
    }

    private fun isVpn(): Boolean {
        return try {
            val networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
            networkInterfaces.any { networkInterface ->
                networkInterface.isUp && (networkInterface.name.contains("tun") ||
                        networkInterface.name.contains("ppp") ||
                        networkInterface.name.contains("pptp"))
            }
        } catch (e: Exception) {
            e.printStackTrace() // Log error if needed
            false
        }
    }
}
