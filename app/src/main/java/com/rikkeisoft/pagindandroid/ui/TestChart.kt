package com.rikkeisoft.pagindandroid.ui

import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader.TileMode
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class TestChart(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var radius: Float = 0.toFloat()

    var paint: Paint? = null
    var shadowPaint: Paint? = null

    var myPath: Path? = null
    var shadowPath: Path? = null

    var outterCircle: RectF? = null
    var innerCircle: RectF? = null
    var shadowRectF: RectF? = null

    init {
        val a = context?.theme?.obtainStyledAttributes(
            attrs,
            com.rikkeisoft.pagindandroid.R.styleable.DonutChart,
            0, 0
        )

        try {
            radius = a?.getDimension(com.rikkeisoft.pagindandroid.R.styleable.DonutChart_radius, 20.0f)!!.toFloat()
        } finally {
            a?.recycle()
        }

        paint = Paint()
        paint?.isDither = true
        paint?.style = Paint.Style.FILL
        paint?.strokeJoin = Paint.Join.ROUND
        paint?.strokeCap = Paint.Cap.ROUND
        paint?.isAntiAlias = true
        paint?.strokeWidth = radius / 14.0f

        shadowPaint = Paint()
        shadowPaint?.color = -0x10000000
        shadowPaint?.style = Paint.Style.STROKE
        shadowPaint?.isAntiAlias = true
        shadowPaint?.strokeWidth = 6.0f
        shadowPaint?.maskFilter = BlurMaskFilter(4f, BlurMaskFilter.Blur.SOLID)


        myPath = Path()
        shadowPath = Path()


        outterCircle = RectF()
        innerCircle = RectF()
        shadowRectF = RectF()

        var adjust = .019f * radius
        shadowRectF?.set(adjust, adjust, radius * 2 - adjust, radius * 2 - adjust)

        adjust = .038f * radius
        outterCircle?.set(adjust, adjust, radius * 2 - adjust, radius * 2 - adjust)

        adjust = .276f * radius
        innerCircle?.set(adjust, adjust, radius * 2 - adjust, radius * 2 - adjust)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // draw shadow
        paint?.shader = null
        val adjust = .0095f * radius
        paint?.setShadowLayer(8F, adjust, -adjust, -0x56000000)
        drawDonut(canvas, paint, 0F, 359.9f)

        // green
        setGradient(-0x7b43c3, -0xa477d7)
        drawDonut(canvas, paint, 0F, 60F)

        //red
        setGradient(-0x1fb5d1, -0x48e9e5)
        drawDonut(canvas, paint, 60F, 60F)

        // blue
        setGradient(-0xb5493f, -0xde7d53)
        drawDonut(canvas, paint, 120F, 60F)

        // yellow
        setGradient(-0x100, -0x12cdb)
        drawDonut(canvas, paint, 180F, 180F)
    }

    private fun setGradient(sColor: Int, eColor: Int) {
        paint?.shader = RadialGradient(
            radius, radius, radius - 5,
            intArrayOf(sColor, eColor),
            floatArrayOf(.6f, .95f), TileMode.CLAMP
        )
    }

    private fun drawDonut(canvas: Canvas?, paint: Paint?, start: Float, sweep: Float) {
        myPath?.reset()
        myPath?.arcTo(outterCircle, start, sweep, false)
        myPath?.arcTo(innerCircle, start + sweep, -sweep, false)
        myPath?.close()
        canvas?.drawPath(myPath!!, paint!!)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val desiredWidth = radius.toInt() * 2
        val desiredHeight = radius.toInt() * 2

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width: Int
        val height: Int

        //70dp exact
        width = if (widthMode == MeasureSpec.EXACTLY) {
            widthSize
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //wrap content
            Math.min(desiredWidth, widthSize)
        } else {
            desiredWidth
        }

        //Measure Height
        height = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else if (heightMode == MeasureSpec.AT_MOST) {
            min(desiredHeight, heightSize)
        } else {
            desiredHeight
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height)
    }
}