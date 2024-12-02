package com.demo.study.widget.nest

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chl.common.utils.LogUtil

/**
 * create on 2024/11/19
 * @author chenglong
 * description :
 */
class MyRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : RecyclerView(context, attrs) {

    companion object{

        private const val TAG = "MyRecyclerView"
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val b = super.dispatchTouchEvent(ev)
        LogUtil.e("AAAA", "$TAG ;; dispatchTouchEvent :: action = ${ev.action} ;; y = ${ev.y} ;; b = $b")
        return b
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val b = super.onInterceptTouchEvent(ev)
        LogUtil.e("AAAA", "$TAG ;; onInterceptTouchEvent :: action = ${ev.action} ;; y = ${ev.y} ;; b = $b")
        return b
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val b = super.onTouchEvent(ev)
        LogUtil.e("AAAA", "$TAG ;; onTouchEvent :: action = ${ev.action} ;; y = ${ev.y} ;; b = $b")
        return b
    }

    override fun isNestedScrollingEnabled(): Boolean {
        val b = super.isNestedScrollingEnabled()
        LogUtil.e("AAAA", "$TAG ;; isNestedScrollingEnabled :: b = $b")
        return b
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        super.setNestedScrollingEnabled(enabled)
        LogUtil.e("AAAA", "$TAG ;; setNestedScrollingEnabled :: b = $enabled")
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
        val b = super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
        LogUtil.e("AAAA", "$TAG ;; dispatchNestedPreScroll :: dy = $dy ;; consumed = $consumed ;; offset = $offsetInWindow ;; b = $b")
        return b
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?, type: Int): Boolean {
        val b = super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)
        LogUtil.e("AAAA", "$TAG ;; dispatchNestedPreScroll :: dy = $dy ;; consumed = $consumed ;; offset = $offsetInWindow ;; type = $type ;; b = $b")
        return b
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        val b = super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
        LogUtil.e("AAAA", "$TAG ;; dispatchNestedScroll :: dyConsumed = $dyConsumed ;; dyUnconsumed = $dyUnconsumed ;; offset = $offsetInWindow ;; b = $b")
        return b
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        val b = super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type)
        LogUtil.e("AAAA", "$TAG ;; dispatchNestedScroll :: dyConsumed = $dyConsumed ;; dyUnconsumed = $dyUnconsumed ;; offset = $offsetInWindow ;; type = $type ;; b = $b")
        return b
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        val b = super.dispatchNestedPreFling(velocityX, velocityY)
        LogUtil.e("AAAA", "$TAG ;; dispatchNestedPreFling :: velocityY = $velocityY ;; b = $b")
        return b
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        val b = super.dispatchNestedFling(velocityX, velocityY, consumed)
        LogUtil.e("AAAA", "$TAG ;; dispatchNestedFling :: velocityY = $velocityY ;; consumed = $consumed ;; b = $b")
        return b
    }

    override fun startNestedScroll(axes: Int): Boolean {
        val b = super.startNestedScroll(axes)
        LogUtil.e("AAAA", "$TAG ;; startNestedScroll :: axes = $axes ;; b = $b")
        return b
    }

    override fun startNestedScroll(axes: Int, type: Int): Boolean {
        val b = super.startNestedScroll(axes, type)
        LogUtil.e("AAAA", "$TAG ;; startNestedScroll :: axes = $axes ;; type = $type ;; b = $b")
        return b
    }

    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        val b = super.onStartNestedScroll(child, target, nestedScrollAxes)
        LogUtil.e("AAAA", "$TAG ;; onStartNestedScroll :: nestedScrollAxes = $nestedScrollAxes ;; b = $b ;; view = $target")
        return b
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        val b = super.onNestedPreFling(target, velocityX, velocityY)
        LogUtil.e("AAAA", "$TAG ;; onNestedPreFling :: velocityY = $velocityY ;; b = $b ;; view = $target")
        return b
    }

    override fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        val b = super.onNestedFling(target, velocityX, velocityY, consumed)
        LogUtil.e("AAAA", "$TAG ;; onNestedFling :: velocityY = $velocityY ;; consumed = $consumed ;; b = $b ;; view = $target")
        return b
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        super.onNestedPreScroll(target, dx, dy, consumed)
        LogUtil.e("AAAA", "$TAG ;; onNestedPreScroll :: dy = $dy ;; consumed = $consumed ;; view = $target")
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        LogUtil.e("AAAA", "$TAG ;; onNestedScroll :: dyConsumed = $dyConsumed ;; dyUnconsumed = $dyUnconsumed ;; view = $target")
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int) {
        super.onNestedScrollAccepted(child, target, axes)
        LogUtil.e("AAAA", "$TAG ;; onNestedScrollAccepted :: axes = $axes ;; view = $target")
    }

    override fun stopNestedScroll() {
        super.stopNestedScroll()
        LogUtil.e("AAAA", "$TAG ;; stopNestedScroll")
    }

    override fun stopNestedScroll(type: Int) {
        super.stopNestedScroll(type)
        LogUtil.e("AAAA", "$TAG ;; stopNestedScroll :: type = $type")
    }

    override fun onStopNestedScroll(child: View) {
        super.onStopNestedScroll(child)
        LogUtil.e("AAAA", "$TAG ;; onStopNestedScroll :: view = $child")
    }









}