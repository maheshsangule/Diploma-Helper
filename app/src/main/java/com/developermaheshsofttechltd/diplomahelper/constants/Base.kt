package com.developermaheshsofttechltd.diplomahelper.constants

import android.app.Dialog
import android.content.Context
import android.widget.Toast
import com.developermaheshsofttechltd.diplomahelper.R


object Base {

    fun showProgressBar(context: Context): Dialog {
        val dialogue = Dialog(context)
        dialogue.setContentView(R.layout.progressbar)
        dialogue.show()

        return dialogue
    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


}