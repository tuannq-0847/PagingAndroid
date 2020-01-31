package com.rikkeisoft.pagindandroid.ui.adapter

import android.animation.ValueAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.doOnLayout
import androidx.core.view.doOnNextLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rikkeisoft.pagindandroid.R

class TestAdapter(private val tests: MutableList<String>) :
    RecyclerView.Adapter<TestAdapter.TestHolder>() {

    private var originalHeight: Int = 0
    private var expandedHeight: Int = 0
    private lateinit var recyclerView: RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
        return TestHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_test,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return tests.size
    }

    override fun onBindViewHolder(holder: TestHolder, position: Int) {
        var expand = false
        val data = tests[position]
        Log.d("dataPos", data)
//        holder.title.text = data
        //  expandItem(holder, expand, false)
        holder.itemView.setOnClickListener {
            expandItem(holder, !expand, true)
            expand = !expand
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onViewAttachedToWindow(holder: TestHolder) {
        super.onViewAttachedToWindow(holder)
        // get originalHeight & expandedHeight if not gotten before
        if (expandedHeight < 0) {
            expandedHeight = 0 // so that this block is only called once

            holder.cardContainer.doOnLayout { view ->
                originalHeight = view.height

                // show expandView and record expandedHeight in next
                // layout pass (doOnNextLayout) and hide it immediately
                holder.expandView.isVisible = true
                holder.cardContainer.doOnNextLayout { view ->
                    expandedHeight = view.height

                    // We use post{} to hide the view. Otherwise it will not
                    // lay it out again, since this block is done on the layout pass
                    holder.expandView.post {
                        holder.expandView.isVisible = false
                    }
                }
            }
        }
    }

    private inline val LinearLayoutManager.visibleItemsRange: IntRange
        get() = findFirstVisibleItemPosition()..findLastVisibleItemPosition()

    private fun expandItem(holder: TestHolder, expand: Boolean, animate: Boolean) {
        if (animate) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val animator = if (expand) ValueAnimator.ofFloat(0f, 1f)
            else ValueAnimator.ofFloat(1f, 0f)
            animator.run {
                addUpdateListener {
                    val progress = it.animatedValue as Float
                    for (i in layoutManager.visibleItemsRange) {

                    }
                }
                duration = 1000
                interpolator = AccelerateDecelerateInterpolator()
            }
            if (expand) animator.doOnStart {
                holder.expandView.isVisible = true
            }
            else animator.doOnEnd { holder.expandView.isVisible = false }
        } else {
            holder.expandView.isVisible = expand && expandedHeight >= 0
            setExpandProgress(holder, if (expand) 1f else 0f)
        }
    }

    private fun setExpandProgress(holder: TestHolder, progress: Float) {
        if (expandedHeight > 0 && originalHeight > 0) {
            holder.cardContainer.layoutParams.height =
                (originalHeight + (expandedHeight - originalHeight) * progress).toInt()
        }
        holder.cardContainer.layoutParams.width =
            (originalHeight + (expandedHeight - originalHeight) * progress).toInt()

        holder.cardContainer.requestLayout()
        holder.chevron.rotation = 90 * progress
    }

    inner class TestHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var expandView = itemView.findViewById<View>(R.id.expand_view)
        var cardContainer = itemView.findViewById<View>(R.id.card_container)
        var chevron = itemView.findViewById<View>(R.id.chevron)
        var title = itemView.findViewById<TextView>(R.id.title)
    }

//    fun getValueAnimator(
//        forward: Boolean = true,
//        duration: Long,
//        interpolator: TimeInterpolator
//    ): ValueAnimator {
//        val animator = if (forward) ValueAnimator.ofFloat(0f,1f)
//        else ValueAnimator.ofFloat(1f,0f)
//        animator.addUpdateListener {  }
//    }
}
