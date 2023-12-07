package com.chl.common.widget.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
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

    override fun onDraw(canvas: Canvas) {
        val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        if (dates.isNotEmpty()) {
            dates.forEach {
                paint.color = it.color
                canvas.drawArc(rect,it.startAngle,it.sweepAngle,true,paint)
            }
        }
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