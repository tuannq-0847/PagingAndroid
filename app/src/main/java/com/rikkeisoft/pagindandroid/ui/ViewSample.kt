package com.rikkeisoft.pagindandroid.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ViewSample(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val size = 320

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawFaceBackground(canvas)
        drawEyes(canvas)
        dwawMouth(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas?) {
        paint.color = Color.YELLOW
        paint.style = Paint.Style.FILL

        val radius = size / 2f
        canvas?.drawCircle(size / 2f, size / 2f, radius, paint)
//
//        paint.color = Color.RED
//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 4.0f
//
//        // 5
//        canvas?.drawCircle(size / 2f, size / 2f, radius - 4.0f / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas?) {
    }

    private fun dwawMouth(canvas: Canvas?) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}
