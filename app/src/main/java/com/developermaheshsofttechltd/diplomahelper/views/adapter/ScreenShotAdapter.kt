package com.developermaheshsofttechltd.diplomahelper.views.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.developermaheshsofttechltd.diplomahelper.databinding.ItemScreenshotsBinding
import com.developermaheshsofttechltd.diplomahelper.models.ScreenShotModel
import com.developermaheshsofttechltd.diplomahelper.utils.loadOnline

class ScreenShotAdapter(
    private val context: Activity,
    private val navItemList: ArrayList<ScreenShotModel>
) : RecyclerView.Adapter<ScreenShotAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemScreenshotsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ScreenShotModel) {

            binding.apply {
//                ivItemBookImage.setImageURI(model.imageScreenShot.toUri())
                ivItemBookImage.loadOnline(model.imageScreenShot)
            }
//            with(binding) {
////                ivImage.setImageResource(model.courseIcon)
////                tvText.text = model.courseTitle
//
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemScreenshotsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = navItemList[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int = navItemList.size
}
