package com.developermaheshsofttechltd.diplomahelper.utils

import android.content.Context
import android.content.SharedPreferences
import com.developermaheshsofttechltd.diplomahelper.R

class SharedPrefUtils private constructor() {

    companion object {
        private var preferences: SharedPreferences? = null
    }

    fun init(context: Context) {
        val appName = context.getString(R.string.app_name).replace(" ", "_")
        preferences = context.getSharedPreferences("${appName}_prefs", Context.MODE_PRIVATE)
    }

    fun getPrefString(key: String, defaultValue: String): String {
        return preferences?.getString(key, defaultValue) ?: defaultValue
    }

    fun getPrefBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences?.getBoolean(key, defaultValue) ?: defaultValue
    }

    fun getPrefInt(key: String, defaultValue: Int): Int {
        return preferences?.getInt(key, defaultValue) ?: defaultValue
    }

    fun putPrefString(key: String, value: String): Boolean {
        preferences?.edit()?.putString(key, value)?.apply()
        return true
    }

    fun putPrefInt(key: String, value: Int): Boolean {
        preferences?.edit()?.putInt(key, value)?.apply()
        return true
    }

    fun putPrefBoolean(key: String, value: Boolean): Boolean {
        preferences?.edit()?.putBoolean(key, value)?.apply()
        return true
    }
}
