package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

class Utils(private val context: Context) {

    companion object {
        fun isConnected(context: Activity): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
        }

        fun customToast(context: Activity,msg: String = "") {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }



    }
}
