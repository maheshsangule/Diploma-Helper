package com.developermaheshsofttechltd.diplomahelper.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.developermaheshsofttechltd.diplomahelper.databinding.CourseItemBinding
import com.developermaheshsofttechltd.diplomahelper.models.BranchesModel

class BranchesAdapter(
    private val context: FragmentActivity,
    private val courseList: ArrayList<BranchesModel>
) : RecyclerView.Adapter<BranchesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = courseList[position]
        holder.bind(model, context)
    }

    override fun getItemCount(): Int = courseList.size

    class ViewHolder(private val binding: CourseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: BranchesModel, context: Context) {
            binding.ivImage.setImageURI(model.courseIcon.toUri())
            binding.tvText.text = model.courseTitle
        }
    }
}
