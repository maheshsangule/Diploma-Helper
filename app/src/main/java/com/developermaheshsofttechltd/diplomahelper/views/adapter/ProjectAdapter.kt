package com.developermaheshsofttechltd.diplomahelper.views.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developermaheshsofttechltd.diplomahelper.databinding.ItemProjectBinding
import com.developermaheshsofttechltd.diplomahelper.models.ProjectModel
import com.developermaheshsofttechltd.diplomahelper.models.ScreenShotModel

class ProjectAdapter(
    private val context: Activity,
    private val list: ArrayList<ProjectModel>
) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.bind(context, model)
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(private val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(context: Activity, model: ProjectModel) {
            binding.apply {
                model.apply {
                    val mainPrice =  (projectTotalPrice.toFloat()-projectTotalPrice.toFloat() * projectDiscount.toFloat() / (100)).toInt()

                    ivItemBookImage.setImageResource(projectIcon.toInt())
                    tvProjectTitle.text = projectTitle
                    tvProjectDescription.text = projectDescription
                    tvTotalPrice.text = projectTotalPrice.toString()+"₹"
                    laDiscount.setAnimationFromUrl(projectDiscountAnimation)
//                    laDiscount.loop(true)

                    val screenShotList = ArrayList<ScreenShotModel>()
                    for (i in projectScreenShots) {
                        screenShotList.add(i)
                    }

                    Log.d("VISHAY", "bind: $screenShotList")
                    recyclerView.adapter = ScreenShotAdapter(context, screenShotList)

                    tvProjectPrice.text = mainPrice.toString()+"₹"

                    ivArrow.setOnClickListener {
                        if (ivArrow.rotation == 0.toFloat()) {
                            recyclerView.visibility = View.VISIBLE
                            ivArrow.rotation = 180.toFloat()
                        } else {
                            recyclerView.visibility = View.GONE
                            ivArrow.rotation = 0.toFloat()
                        }


                    }

                }

            }
        }

    }
}

