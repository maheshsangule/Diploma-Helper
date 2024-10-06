package com.developermaheshsofttechltd.diplomahelper.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developermaheshsofttechltd.diplomahelper.databinding.CategoryItemBinding
import com.developermaheshsofttechltd.diplomahelper.utils.SharedPrefUtils

class GridAdapter(
    private val context: Context,
    private val items: List<CategoryModel>,
    private val categoryStat: Map<String, CategoryStats>
) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {


    private var onClickListener: OnClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.categoryImage.setImageResource(item.image)
        holder.binding.categoryName.text = item.name

//        val count = categoryStat[item.id]?.total_num_of_questions
//        holder.binding.categoryQuestion.text = "$count questions"


        SharedPrefUtils.init(context)
        holder.binding.root.setOnClickListener {
            onClickListener?.onClick(item.id.toInt())

            SharedPrefUtils.putPrefInt("image", item.image)
            SharedPrefUtils.putPrefString("title", holder.binding.categoryName.text.toString())

//            saveToSharedPreferences(item.image, holder.binding.categoryName.text.toString())
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(id: Int)
    }

//    private fun saveToSharedPreferences(imageResId: Int, text: String) {
//
//        SharedPrefUtils.putPrefInt("image", imageResId)
//        SharedPrefUtils.putPrefString("title", text)
//        val editor = sharedPreferences.edit()
//        editor.putInt("image", imageResId)
//        editor.putString("title", text)
//        editor.apply()
//
//    }
}


//package com.developermaheshsofttechltd.diplomahelper.quiz
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.SharedPreferences
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.developermaheshsofttechltd.diplomahelper.R
//
//
//class GridAdapter(
//    private val sharedPreferences: SharedPreferences, private val context: Context,
//    private val items: List<CategoryModel>,
//    private val categoryStat: Map<String, CategoryStats>
//) :
//    RecyclerView.Adapter<GridAdapter.ViewHolder>() {
//
//    private var onClickListener: OnClickListener? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.category_item2, parent, false)
//        return ViewHolder(view)
//    }
//
//    @SuppressLint("SetTextI18n")
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = items[position]
//        holder.image.setImageResource(item.image)
//        holder.tvCategoryName.text = item.name
//        val count = categoryStat[item.id]?.total_num_of_questions
//        holder.tvQuestionCount.text = count.toString() + " questions"
//
//        holder.itemView.setOnClickListener {
//            if (onClickListener != null) {
//                onClickListener!!.onClick(item.id.toInt())
//                saveToSharedPreferences(item.image, holder.tvCategoryName.text.toString())
//            }
//        }
//    }
//
//    override fun getItemCount(): Int = items.size
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val image: ImageView = itemView.findViewById(R.id.category_image)
//        val tvCategoryName: TextView = itemView.findViewById(R.id.category_name)
//        val tvQuestionCount: TextView = itemView.findViewById(R.id.category_question)
//    }
//
//    fun setOnClickListener(onClickListener: OnClickListener) {
//        this.onClickListener = onClickListener
//
//    }
//
//    interface OnClickListener {
//        fun onClick(id: Int)
//    }
//
//    private fun saveToSharedPreferences(imageResId: Int, text: String) {
//        val editor = sharedPreferences.edit()
//        editor.putInt("image", imageResId)
//        editor.putString("title", text)
//        editor.apply()
//    }
//}
