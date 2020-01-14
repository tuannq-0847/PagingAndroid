package com.rikkeisoft.pagindandroid.ui

import android.animation.ValueAnimator
import android.graphics.PointF
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.rikkeisoft.pagindandroid.R
import kotlinx.android.synthetic.main.activity_moce_page.*


class TestActivity : AppCompatActivity() {

    var height = 0
    var width = 0

    var isPage1Show: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moce_page)
        getScreenSize()
//        Log.e("TestActivity", "$width:$height")
        page_1.translationX = -.1f * width
        page_2.translationX = .9f * width

        main_container.setOnTouchListener(onPageTouch)

    }

    var clickPoint = PointF()
    var page1X = 0f
    var page2X = 0f

    var onPageTouch = View.OnTouchListener { v, event ->

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                clickPoint = PointF(event.x, event.y)
                page1X = page_1.translationX
                page2X = page_2.translationX
            }
            MotionEvent.ACTION_MOVE -> {
                val rangeX = event.x - clickPoint.x
                page_1.translationX = page1X + rangeX
                page_2.translationX = page2X + rangeX
            }

            MotionEvent.ACTION_UP -> {
                isPage1Show = page_2.translationX < .5f * width
                showIndicator()
            }
        }


        return@OnTouchListener false
    }

    fun animationText1Rotate(isShow: Boolean) {
        var from = 0.5f
        var to = 1.8f
        if (isShow) {
            from = 0.5f
            to = 1.8f
        } else {
            to = 0.5f
            from = 1.8f
        }
        ValueAnimator.ofFloat(from, to)
            .apply {
                duration = 1000
                start()
            }
            .addUpdateListener {
                Log.d("valueAnimator", it.animatedValue.toString())
                val rotate = RotateAnimation(
                    0f,
                    -270f,
                    Animation.RELATIVE_TO_SELF,
                    it.animatedValue as Float,
                    Animation.RELATIVE_TO_SELF,
                    it.animatedValue as Float
                )
                rotate.duration = 600
                rotate.interpolator = LinearInterpolator()
                rotate.fillAfter = true
                textPage1.startAnimation(rotate)
            }
    }

    fun animationText2Rotate(isShow: Boolean) {
        var from = 0.5F
        var to = -0.5F
        if (isShow) {
            from = 0.5F
            to = -0.5F
        } else {
            to = 0.5f
            from = -0.5F
        }
        ValueAnimator.ofFloat(from, to)
            .apply {
                duration = 1000
                start()
            }
            .addUpdateListener {
                val rotate = RotateAnimation(
                    0f,
                    270f,
                    Animation.RELATIVE_TO_SELF,
                    it.animatedValue as Float,
                    Animation.RELATIVE_TO_SELF,
                    it.animatedValue as Float
                )
                rotate.duration = 600
                rotate.interpolator = LinearInterpolator()
                rotate.fillAfter = true
                textPage2.startAnimation(rotate)
            }
    }

    fun page1Hide() {
        page_1.animate()
            .setDuration(500)
            .translationX(-.9f * width)
            .start()
    }

    fun page1Show() {
        page_1.animate()
            .setDuration(500)
            .translationX(-.1f * width)
            .start()
    }

    fun page2Hide() {
        page_2.animate()
            .setDuration(500)
            .translationX(.9f * width)
            .start()
    }

    fun page2Show() {
        page_2.animate()
            .setDuration(500)
            .translationX(.1f * width)
            .start()
    }

    fun showIndicator() {
        if (isPage1Show) {
            page1Hide()
            animationText1Rotate(true)
            page2Show()
            animationText2Rotate(false)
        } else {
            page1Show()
            animationText2Rotate(true)
            page2Hide()
            animationText1Rotate(false)
        }
        isPage1Show = !isPage1Show
    }

    fun getScreenSize() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = displayMetrics.heightPixels
        width = displayMetrics.widthPixels
    }
}