package com.developermaheshsofttechltd.diplomahelper.utils

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.developermaheshsofttechltd.diplomahelper.R

class FragmentUtils {
    companion object {
        fun loadFragment(fragment: Fragment, activity: AppCompatActivity) {
            try {
                val transaction = activity.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.commitNow()
            } catch (e: Exception) {
                Log.e("Kerber", "Error replacing fragment: ${e.message}")
            }
        }

    }
}