package com.developermaheshsofttechltd.diplomahelper.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import java.io.File

const val FOLDER_PATH = "Bachelor Helper"

fun ImageView.loadOnline(imageUrl: String) {
    @Suppress("DEPRECATION")
    Glide.with(this.context)
        .load(imageUrl)
        .fitCenter()
        .transition(DrawableTransitionOptions.withCrossFade())
        .thumbnail(0.1f)
        .into(this)
}

fun ImageView.loadUserProfile(imageUrl: String) {
    Log.d("FrontEndUtils", "Loading user profile image from URL: $imageUrl")

    Glide.with(this.context)
        .load(Uri.parse(imageUrl))
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
        .into(this)
}

fun sharePdf(context: Activity, pdfTitle: String) {
    try {
        val pdfFile = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "/$FOLDER_PATH/$pdfTitle.pdf"
        )
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            pdfFile
        )

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(Intent.createChooser(shareIntent, "Share PDF using:"))
    } catch (e: Exception) {
        Log.d("Share", e.localizedMessage ?: "Error sharing PDF")
    }
}
