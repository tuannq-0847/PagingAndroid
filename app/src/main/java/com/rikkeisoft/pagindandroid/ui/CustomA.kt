package com.rikkeisoft.pagindandroid.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomA(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private var paint = Paint()

    private var cPaint = Paint()
    private var cPaint2 = Paint()
    private var rAx = 20F
    private var rAy = 100F

    private var rBx = 500F
    private var rBy = 120F

    private var cx = rAx
    private var c2x = rBx
    private val cy = (rBy + rAy) / 2
    private val c2y = (rBy + rAy) / 2

    private var round = 10F
    private var cR = 20F
//    private var padding = 20F

//    init {
//        ValueAnimator.ofFloat(0f, rBx / 2)
//            .apply {
//                duration = 5000
//                start()
//            }
//            .addUpdateListener {
//                round = it.animatedValue as Float
//                invalidate()
//            }
//    }

    init {
//        setBackgroundColor(Color.BLACK)
        cPaint.color = Color.GREEN
        cPaint.alpha = 128
        cPaint2.color = Color.BLUE
        cPaint2.alpha = 128
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.color = Color.RED
        canvas.drawRoundRect(RectF(rAx, rAy, rBx, rBy), round, round, paint)
        canvas.drawCircle(cx, cy, cR, cPaint)
        canvas.drawCircle(c2x, c2y, cR, cPaint2)
    }

    var isTouch1 = false
    var isTouch2 = false

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            isTouch1 = event.x >= cx - cR && event.x <= cx + cR
            isTouch2 = event.x >= c2x - cR && event.x <= c2x + cR
            if (isTouch1) {
                cx = event.x
                invalidate()
            }
            if (isTouch2) {
                c2x = event.x
                invalidate()
            }
        } else if (event.action == MotionEvent.ACTION_UP) {
            isTouch1 = false
            isTouch2 = false
        } else if (event.action == MotionEvent.ACTION_MOVE) {
            if (isTouch1) {
                    cx = event.x
                if (cx <= rAx) cx = rAx
                if (cx >= rBx) cx = rBx

                invalidate()
            }
            if (isTouch2) {
                    c2x = event.x
                if (c2x <= rAx) c2x = rAx
                if (c2x >= rBx) c2x = rBx

                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }
}
