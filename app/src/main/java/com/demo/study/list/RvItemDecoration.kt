package com.demo.study.list

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.View
import android.view.View.MeasureSpec
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chl.common.utils.ScreenUtil
import com.demo.study.R

/**
 * create on 2024/9/20
 * @author chenglong
 * description :
 * 参考：https://juejin.cn/post/7382159756175147027?searchId=202409231011235307B2BCD0258F908DC6
 */
class RvItemDecoration(private val ctx: Context) : RecyclerView.ItemDecoration() {

    private val offset = ScreenUtil.dp2px(80f)
    private val textMarginStart = ScreenUtil.dp2px(20f)
    private val paint = Paint()
    private val textPaint = TextPaint()
    private val bounds = Rect()

    init {
        paint.apply {
            color = Color.GREEN
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
        for (i in 0 until count) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            if (isFirstInGroup(position)) {
                val bottom = view.top
                val top = bottom - offset
                val widthSpace = MeasureSpec.makeMeasureSpec(right - left, View.MeasureSpec.EXACTLY)
                val heightSpace = MeasureSpec.makeMeasureSpec(offset, View.MeasureSpec.EXACTLY)
                val headView = LayoutInflater.from(ctx).inflate(R.layout.item_rv_head, parent, false)
                val content = headView.findViewById<TextView>(R.id.tv_content)
                content.text = getGroupText(position)
                headView.measure(widthSpace, heightSpace)
                c.save()
                c.translate(left.toFloat(), top.toFloat())
                headView.layout(left, top, right, bottom)
                headView.draw(c)
                c.restore()
            }
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
        val widthSpace = MeasureSpec.makeMeasureSpec(right - left, View.MeasureSpec.EXACTLY)
        val heightSpace = MeasureSpec.makeMeasureSpec(offset, View.MeasureSpec.EXACTLY)
        val str = getGroupText(position)
        val count = parent.childCount
        for (i in 0 until count) {
            val child = parent.getChildAt(i)
            if (isLastInGroup(position)) {
                val bottom = child.bottom
                if (bottom < offset) {
                    val headView = LayoutInflater.from(ctx).inflate(R.layout.item_rv_head, parent, false)
                    val content = headView.findViewById<TextView>(R.id.tv_content)
                    content.text = str
                    headView.measure(widthSpace, heightSpace)
                    c.save()
                    val top = bottom - offset
                    c.translate(left.toFloat(), top.toFloat())
                    headView.layout(left, top, right, bottom)
                    headView.draw(c)
                    c.restore()
                    return
                }
            }
        }
        val headView = LayoutInflater.from(ctx).inflate(R.layout.item_rv_head, parent, false)
        val content = headView.findViewById<TextView>(R.id.tv_content)
        content.text = str
        headView.measure(widthSpace, heightSpace)
        c.save()
        headView.layout(left, 0, right, offset)
        headView.draw(c)
        c.restore()
    }

    private fun isFirstInGroup(position: Int): Boolean {
        return position % 10 == 0
    }

    private fun isLastInGroup(position: Int): Boolean {
        return position % 10 == 9
    }
}