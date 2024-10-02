package com.developermaheshsofttechltd.diplomahelper.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.developermaheshsofttechltd.diplomahelper.databinding.ItemNavDrawerBinding
import com.developermaheshsofttechltd.diplomahelper.models.BranchesModel

class NavigationAdapter(
    private val context: Context,
    private val navItemList: ArrayList<BranchesModel>
) : RecyclerView.Adapter<NavigationAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemNavDrawerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: BranchesModel) {
            with(binding) {
                ivImage.setImageURI(model.courseIcon.toUri())
                tvText.text = model.courseTitle
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNavDrawerBinding.inflate(
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
