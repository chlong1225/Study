package com.chl.common.widget.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.graphics.Color
import android.view.MotionEvent
import android.view.View

/**
 * create on 2023/12/7
 * @author chenglong
 * description : 自定义饼状图
 */
class PieView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private val dates = mutableListOf<PieBean>()

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    //手指按压下的位置
    private var downX = 0f
    private var downY = 0f
    private var isShowTouch = false

    private val touchPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas) {
        val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        if (dates.isNotEmpty()) {
            dates.forEach {
                paint.color = it.color
                canvas.drawArc(rect,it.startAngle,it.sweepAngle,true,paint)
            }
        }
        if (isShowTouch) {
            canvas.drawCircle(downX,downY,5f,touchPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            downX = event.x
            downY = event.y
            isShowTouch = true
            invalidate()
        } else if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL) {
            isShowTouch = false
            invalidate()
        }
        return true
    }

    fun setDates(beans: List<PieBean>) {
        dates.clear()
        dates.addAll(beans)
        calculate()
        invalidate()
    }

    private fun calculate() {
        var total = 0.0
        dates.forEach {
            total += it.value
        }
        var startAngle = 0f
        for (i in dates.indices) {
            dates[i].startAngle = startAngle
            dates[i].sweepAngle = (dates[i].value / total * 360).toFloat()
            startAngle += dates[i].sweepAngle
        }
    }

    data class PieBean(
        val value: Double,
        val color: Int,
        var startAngle: Float = 0f,
        var sweepAngle: Float = 0f
    )
}