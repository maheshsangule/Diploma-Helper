package com.developermaheshsofttechltd.diplomahelper.constants

import android.app.ActionBar
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.databinding.ProgressbarBinding


object Base {
    private lateinit var dialog: Dialog
    fun showProgressBar(context: Activity,isShow:Boolean): Dialog {
        val dialogBinding = ProgressbarBinding.inflate(context.layoutInflater)

        dialog = Dialog(context).apply {
            setCancelable(false)
            setContentView(dialogBinding.root)
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window!!.setLayout(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            if(isShow)
                show()
            else
                dismiss()
        }


//        dialogue.setContentView(R.layout.progressbar)
//        context.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//        dialogue.show()
//
        return dialog
    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


}