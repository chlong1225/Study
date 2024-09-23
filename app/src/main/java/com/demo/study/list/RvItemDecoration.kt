package com.demo.study.list

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chl.common.utils.LogUtil
import com.chl.common.utils.ScreenUtil

/**
 * create on 2024/9/20
 * @author chenglong
 * description :
 */
class RvItemDecoration : RecyclerView.ItemDecoration() {

    private val offset = ScreenUtil.dp2px(50f)
    private val textMarginStart = ScreenUtil.dp2px(20f)
    private val paint = Paint()
    private val textPaint = TextPaint()
    private val bounds = Rect()

    init {
        paint.apply {
            color = Color.RED
            isAntiAlias = true
            style = Paint.Style.FILL
        }
        textPaint.apply {
            color = Color.parseColor("#1f2f3f")
            textSize = ScreenUtil.sp2px(16f).toFloat()
            isAntiAlias = true
            isFakeBoldText = true
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (isFirstInGroup(position)) {
            outRect.top = offset
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val count = parent.childCount
        LogUtil.e("AAAA", "left = $left ;; right = $right ;; count = $count")
        for (i in 0 until count) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            if (isFirstInGroup(position)) {
                val bottom = view.top
                val top = bottom - offset
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
                val str = getGroupText(position)
                textPaint.getTextBounds(str, 0, str.length, bounds)
                val y = bottom - (offset / 2.0f - bounds.height() / 2.0f)
                c.drawText(str, left.toFloat() + textMarginStart, y, textPaint)
            }
            LogUtil.e("AAAA","position = $position")
        }
    }

    private fun getGroupText(position: Int): String {
        val count = position / 10 + 1
        return "第${count}组数据"
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val manager = parent.layoutManager as LinearLayoutManager
        val position = manager.findFirstVisibleItemPosition()
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val str = getGroupText(position)
        textPaint.getTextBounds(str, 0, str.length, bounds)
        val count = parent.childCount
        for (i in 0 until count) {
            val child = parent.getChildAt(i)
            if (isLastInGroup(position)) {
                val bottom = child.bottom
                if (bottom < offset) {
                    c.drawRect(left.toFloat(), 0f, right.toFloat(), bottom.toFloat(), paint)
                    val y = bottom - (offset / 2.0f - bounds.height() / 2.0f)
                    c.drawText(str, left.toFloat() + textMarginStart, y, textPaint)
                    return
                }
            }
        }
        c.drawRect(left.toFloat(), 0f, right.toFloat(), offset.toFloat(), paint)
        val y = offset - (offset / 2.0f - bounds.height() / 2.0f)
        c.drawText(str, left.toFloat() + textMarginStart, y, textPaint)
    }

    private fun isFirstInGroup(position: Int): Boolean {
        return position % 10 == 0
    }

    private fun isLastInGroup(position: Int): Boolean {
        return position % 10 == 9
    }
}