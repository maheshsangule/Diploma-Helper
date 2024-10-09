package com.developermaheshsofttechltd.diplomahelper.views.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.developermaheshsofttechltd.diplomahelper.databinding.Item1HomefragBinding
import com.developermaheshsofttechltd.diplomahelper.models.GroupModel
import com.developermaheshsofttechltd.diplomahelper.quiz.QuizActivity
import com.developermaheshsofttechltd.diplomahelper.views.activities.CourseVideosActivity

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


                when (binding.tvTitle.text.toString().lowercase()) {
                    "quiz" -> {
                        context.startActivity(Intent(context, QuizActivity::class.java))
                    }

                    "courses videos" -> {
                        context.startActivity(Intent(context, CourseVideosActivity::class.java))
                    }


                }



            }
        }
    }
}
