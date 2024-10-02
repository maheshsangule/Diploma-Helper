package com.developermaheshsofttechltd.diplomahelper.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developermaheshsofttechltd.diplomahelper.databinding.HorizantalScrollviewItemBinding
import com.developermaheshsofttechltd.diplomahelper.models.HorizontalScrollViewModel
import com.developermaheshsofttechltd.diplomahelper.utils.loadOnline

class HorizontalScrollViewAdapter(
    private val context: Context,
    private val horizontalScrollview: ArrayList<HorizontalScrollViewModel>
) : RecyclerView.Adapter<HorizontalScrollViewAdapter.HomeItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val binding = HorizantalScrollviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        val model = horizontalScrollview[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int = horizontalScrollview.size

    inner class HomeItemViewHolder(private val binding: HorizantalScrollviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: HorizontalScrollViewModel) {
//         loadOnline(binding.ivItemBookImage, model.url)
            binding.ivItemBookImage.loadOnline(model.url)
        }
    }
}
