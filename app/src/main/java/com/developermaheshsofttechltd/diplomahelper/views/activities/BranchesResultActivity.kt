package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developermaheshsofttechltd.diplomahelper.R

class BranchesResultActivity : AppCompatActivity() {

    private val names = listOf(
        "mahesh", "ajay", "rahul", "vishal", "harshad", "krishna", "gopal", "vaibhav",
        "mahesh", "ajay", "rahul", "vishal", "harshad", "krishna", "gopal", "vaibhav",
        "mahesh", "ajay", "rahul", "vishal", "harshad", "krishna", "gopal", "vaibhav"
    )

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_branches_result)
        supportActionBar?.hide()

        searchView = findViewById(R.id.activity_branches_result_sv_search)
        recyclerView = findViewById(R.id.activity_branches_result_rv)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@BranchesResultActivity)
            // Set your adapter here, e.g. recyclerView.adapter = BranchesAdapter(names)
        }

        setupSearchView()
    }

    private fun setupSearchView() {
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                // Implement your search logic here, e.g. filter the list and update the adapter
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Optional: Update the list dynamically as the user types
                return false
            }
        })
    }
}
