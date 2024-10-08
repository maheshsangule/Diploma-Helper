package com.developermaheshsofttechltd.diplomahelper.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.databinding.FragmentHomeBinding
import com.developermaheshsofttechltd.diplomahelper.models.BranchesModel
import com.developermaheshsofttechltd.diplomahelper.models.GroupModel
import com.developermaheshsofttechltd.diplomahelper.models.HorizontalScrollViewModel
import com.developermaheshsofttechltd.diplomahelper.views.adapter.BranchesAdapter
import com.developermaheshsofttechltd.diplomahelper.views.adapter.GroupAdapter
import com.developermaheshsofttechltd.diplomahelper.views.adapter.HorizontalScrollViewAdapter
import java.util.Collections

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null

    // Use final keyword and immutable collections wherever possible
    private val mainImageList: List<Int> = Collections.unmodifiableList(
        listOf(
            R.drawable.ic_courses_videos,
            R.drawable.ic_explore,
            R.drawable.ic_projects
        )
    )
    private val mainTextList: List<String> = Collections.unmodifiableList(
        listOf("Courses Videos", "Explore", "Projects")
    )
    private val branchesImageList: List<Int> = Collections.unmodifiableList(
        listOf(R.drawable.ic_computer)
    )
    private val branchesTextList: List<String> = Collections.unmodifiableList(
        listOf("Computer")
    )
    private val sliderImageList: List<String> = Collections.unmodifiableList(
        listOf("https://devmahesh.excellencescholar.com/Bachelor%20Helper/SliderHome/slider3.png")
    )
    private val importantImageList: List<Int> = Collections.unmodifiableList(
        listOf(R.drawable.result_img, R.drawable.ic_chatgpt, R.drawable.quiz_img)
    )
    private val importantTextList: List<String> = Collections.unmodifiableList(
        listOf("Result", "Chat GPT", "Quiz")
    )
    private val horScrollImageList: List<String> = Collections.unmodifiableList(
        listOf("https://devmahesh.excellencescholar.com/Bachelor%20Helper/SliderHome/slider4.png")
    )
    private val academicImageList: List<Int> = Collections.unmodifiableList(
        listOf(R.drawable.ic_computer)
    )
    private val academicTextList: List<String> = Collections.unmodifiableList(
        listOf("Mechanical")
    )

    // Use ArrayLists for dynamic content
    private val courseList = ArrayList<BranchesModel>()
    private val groupList = ArrayList<GroupModel>()
    private val impList = ArrayList<GroupModel>()
    private val horizontalList = ArrayList<HorizontalScrollViewModel>()
    private val academicList = ArrayList<BranchesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeGroupList()
        initializeCourseList()
        initializeImpList()
        initializeHorizontalList()
        initializeAcademicList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupAdapters()
        setupImageSlider()
        return binding!!.root
    }

    private fun initializeGroupList() {
        groupList.clear()
        for (i in mainImageList.indices) {
            groupList.add(GroupModel(mainImageList[i], mainTextList[i]))
        }
    }

    private fun initializeCourseList() {
        courseList.clear()
        for (i in 0..11) {
            courseList.add(BranchesModel(branchesImageList[0], branchesTextList[0]))
        }
    }

    private fun initializeImpList() {
        impList.clear()
        for (i in importantImageList.indices) {
            impList.add(GroupModel(importantImageList[i], importantTextList[i]))
        }
    }

    private fun initializeHorizontalList() {
        horizontalList.clear()
        for (i in 0..4) {
            horizontalList.add(HorizontalScrollViewModel(horScrollImageList[0]))
        }
    }

    private fun initializeAcademicList() {
        academicList.clear()
        for (i in 0..7) {
            academicList.add(BranchesModel(academicImageList[0], academicTextList[0]))
        }
    }

    private fun setupAdapters() {
        val activity = requireActivity()
        binding!!.topRecyclerView.adapter = GroupAdapter(activity, groupList)
        binding!!.branchesRecyclerView.adapter = BranchesAdapter(activity, courseList)
        binding!!.impRecyclerView.adapter = GroupAdapter(activity, impList)
        binding!!.horScrollView.adapter = HorizontalScrollViewAdapter(activity, horizontalList)
        binding!!.academicRecyclerView.adapter = BranchesAdapter(activity, academicList)
    }

    private fun setupImageSlider() {
        val slideModels = ArrayList<SlideModel>()
        for (i in 0..4) {
            slideModels.add(SlideModel(sliderImageList[0], ScaleTypes.FIT))
        }
        binding!!.imageSlider.setSlideAnimation(AnimationTypes.DEPTH_SLIDE)
        binding!!.imageSlider.setImageList(slideModels, ScaleTypes.FIT)
    }
}