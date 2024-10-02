package com.developermaheshsofttechltd.diplomahelper.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnFlingListener

class SpringScrollHelper {
    private var maxVelocity = 2.0f
    private var flingVx = 0
    private var flingVy = 0
    private var recyclerView: RecyclerView? = null

    // Fling listener for the RecyclerView
    private val recyclerViewFlingListener: OnFlingListener = object : OnFlingListener() {
        override fun onFling(velocityX: Int, velocityY: Int): Boolean {
            flingVx = velocityX
            flingVy = velocityY
            return false // Return false to allow the RecyclerView to handle the fling
        }
    }

    // Scroll listener for the RecyclerView
    private val recyclerViewScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                // Implement custom scroll behavior if needed
            }
        }

    fun attachToRecyclerView(recyclerView: RecyclerView?) {
        if (recyclerView != null) {
            this.recyclerView = recyclerView
            setup()
        } else {
            remove()
        }
    }

    fun setMaxVelocity(maxVelocity: Float) {
        this.maxVelocity = maxVelocity
    }

    private fun setup() {
        if (recyclerView != null) {
            recyclerView!!.overScrollMode = RecyclerView.OVER_SCROLL_NEVER // Disable overscroll
            recyclerView!!.onFlingListener = recyclerViewFlingListener
            recyclerView!!.addOnScrollListener(recyclerViewScrollListener)
        }
    }

    private fun remove() {
        if (recyclerView != null) {
            recyclerView!!.onFlingListener = null
            recyclerView!!.removeOnScrollListener(recyclerViewScrollListener)
            recyclerView = null
        }
    }
}
