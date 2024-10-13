package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.PackageInfoCompat
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.databinding.ActivityHomeBinding
import com.developermaheshsofttechltd.diplomahelper.models.BranchesModel
import com.developermaheshsofttechltd.diplomahelper.utils.FragmentUtils.Companion.loadFragment
import com.developermaheshsofttechltd.diplomahelper.views.adapter.NavigationAdapter
import com.developermaheshsofttechltd.diplomahelper.views.fragment.HomeFragment
import com.developermaheshsofttechltd.diplomahelper.views.fragment.ProfileFragment
import com.developermaheshsofttechltd.diplomahelper.views.fragment.ResultFragment
import com.developermaheshsofttechltd.diplomahelper.views.fragment.SearchFragment

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val list = ArrayList<BranchesModel>()
    private val navItemTitleList = listOf(
        "Home", "Admin Login", "Latest Updates", "Download Papers", "Syllabus",
        "MSBTE", "Hall Ticket", "Time Table", "Result", "Share App",
        "Feedback", "Rate us", "About", "Logout"
    )

    private val activity = this
    private val navItemImageList = listOf(
        R.drawable.home_icon, R.drawable.home_icon, R.drawable.home_icon,
        R.drawable.home_icon, R.drawable.home_icon, R.drawable.home_icon,
        R.drawable.home_icon, R.drawable.home_icon, R.drawable.home_icon,
        R.drawable.home_icon, R.drawable.home_icon, R.drawable.home_icon,
        R.drawable.home_icon, R.drawable.home_icon
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadFragment(HomeFragment(), activity)

        binding.appbarlayout.toolBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        list.clear()
        navItemTitleList.forEachIndexed { index, title ->
            list.add(BranchesModel(navItemImageList[index], title))
        }

        binding.navView.navRecyclerView.adapter = NavigationAdapter(this, list)
        binding.navView.tvVersionCode.text = "Version: ${
            PackageInfoCompat.getLongVersionCode(
                packageManager.getPackageInfo(packageName, 0)
            )
        }.0"

        binding.chipNavigationBar.setItemSelected(R.id.menu_home, true)
        binding.chipNavigationBar.setOnItemSelectedListener {
            when (it) {
                R.id.menu_home -> {
                    loadFragment(HomeFragment(), activity)
                    showToolBar()
                }


                R.id.menu_search -> {
                    loadFragment(SearchFragment(), activity)
                    hideToolBar()
                }


                R.id.menu_video -> {
                    loadFragment(ResultFragment(), activity)
                    showToolBar()
                }
                R.id.menu_profile -> {
                    loadFragment(ProfileFragment(), activity)
                    showToolBar()
                }

                else -> {
                    loadFragment(HomeFragment(), activity)
                    showToolBar()
                }
            }
        }

        binding.appbarlayout.toolBar.setOnMenuItemClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
            return@setOnMenuItemClickListener true
        }
    }


    private fun hideToolBar() {
        binding.appbarlayout.toolBar.visibility = View.GONE
    }

    private fun showToolBar() {
        binding.appbarlayout.toolBar.visibility = View.VISIBLE
    }

//    private fun replaceFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.activity_home_page_frame_layout, fragment)
//            .commitNow()
//    }
}


//package com.developermaheshsofttechltd.diplomahelper.views.activities
//
//import android.os.Bundle
//import android.view.View
//import androidx.appcompat.app.AppCompatActivity
//import com.developermaheshsofttechltd.diplomahelper.R
//import com.developermaheshsofttechltd.diplomahelper.databinding.ActivityHomePageBinding
//import com.developermaheshsofttechltd.diplomahelper.models.BranchesModel
//import com.developermaheshsofttechltd.diplomahelper.utils.FragmentUtils.Companion.loadFragment
//import com.developermaheshsofttechltd.diplomahelper.views.fragment.HomeFragment
//import com.developermaheshsofttechltd.diplomahelper.views.fragment.ProfileFragment
//import com.developermaheshsofttechltd.diplomahelper.views.fragment.SearchFragment
//import com.developermaheshsofttechltd.diplomahelper.views.fragment.ResultFragment
//
//class HomeActivity : AppCompatActivity() {
//
//    private val activity = this
//    private val list = ArrayList<BranchesModel>()
//    private val navItemTitleList = listOf(
//        "Home",
//        "Admin Login",
//        "Latest Updates",
//        "Download Papers",
//        "Syllabus",
//        "MSBTE",
//        "Hall Ticket",
//        "Time Table",
//        "Result",
//        "Share App",
//        "Feedback",
//        "Rate us",
//        "About",
//        "Logout"
//    )
//
//    private val binding by lazy { ActivityHomePageBinding.inflate(layoutInflater) }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//        loadFragment(HomeFragment(), activity)
//        showToolBar()
//
//
//        binding.apply {
//
//            appbarlayout.toolBar.setNavigationOnClickListener {
//                drawerLayout.open()
//            }
//
//
//            chipNavigationBar.setItemSelected(R.id.menu_home, true)
//            chipNavigationBar.setOnItemSelectedListener { it ->
//                when (it) {
//                    R.id.menu_home -> {
//                        loadFragment(HomeFragment(), activity)
//                        showToolBar()
//                    }
//
//                    R.id.menu_search -> {
//                        loadFragment(SearchFragment(), activity)
//                        hideToolBar()
//                    }
//
//                    R.id.menu_video -> {
//                        loadFragment(ResultFragment(), activity)
//                        hideToolBar()
//                    }
//
//                    R.id.menu_profile -> {
//                        loadFragment(ProfileFragment(), activity)
//                        showToolBar()
//                    }
//
//                }
//
//                appbarlayout.toolBar.setOnMenuItemClickListener {
//
//                    when (it.itemId) {
//                        R.id.menu_notification -> {
////                            checkNotification(activity)
//                        }
//                    }
//                    return@setOnMenuItemClickListener true
//                }
//
//
//            }
//        }
//
//
//    }
//
//
//    private fun hideToolBar() {
//        binding.appbarlayout.toolBar.visibility = View.GONE
//
//    }
//
//    private fun showToolBar() {
//        binding.appbarlayout.toolBar.visibility = View.VISIBLE
//    }
//
//}
