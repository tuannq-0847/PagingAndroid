package com.rikkeisoft.pagindandroid.ui.adapter

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.core.view.doOnNextLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.rikkeisoft.pagindandroid.R
import kotlinx.android.synthetic.main.item_test.view.*

class TestAdapter(private val tests: MutableList<String>) :
    RecyclerView.Adapter<TestAdapter.TestHolder>() {

    private var originalHeight: Int = 0
    private var expandedHeight: Int = 0
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

    private fun expandItem(holder: TestHolder, expand: Boolean, animate: Boolean) {
        if (animate) {

        } else {

        }
    }

    class TestHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var expandView = itemView.findViewById<View>(R.id.expand_view)
        var cardContainer = itemView.findViewById<View>(R.id.card_container)
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
