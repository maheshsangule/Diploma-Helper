package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.content.Context

class SharePrefManager(private val context: Context) {
    private val sharedPrefName = "islogout"

    fun logout() {
        val editor = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }
}
