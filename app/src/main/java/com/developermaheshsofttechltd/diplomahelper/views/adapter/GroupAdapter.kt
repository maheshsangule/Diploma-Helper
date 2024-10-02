package com.developermaheshsofttechltd.diplomahelper.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.developermaheshsofttechltd.diplomahelper.databinding.Item1HomefragBinding
import com.developermaheshsofttechltd.diplomahelper.models.GroupModel

class GroupAdapter(
    private val context: FragmentActivity,
    private val groupList: ArrayList<GroupModel>
) : RecyclerView.Adapter<GroupAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            Item1HomefragBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = groupList[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int = groupList.size

    inner class ViewHolder(private val binding: Item1HomefragBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: GroupModel) {
            binding.ivImage.setImageResource(model.image)
            binding.tvTitle.text = model.title
            binding.root.setOnClickListener {
                // Handle item click
            }
        }
    }
}
