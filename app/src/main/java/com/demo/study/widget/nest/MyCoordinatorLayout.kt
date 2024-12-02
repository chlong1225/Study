package com.demo.study.widget.nest

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.chl.common.utils.LogUtil

/**
 * create on 2024/11/19
 * @author chenglong
 * description :
 */
class MyCoordinatorLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : CoordinatorLayout(context, attrs) {

    companion object{

        private const val TAG = "MyCoordinatorLayout"
    }

    override fun isNestedScrollingEnabled(): Boolean {
        val isNestedScrollingEnabled = super.isNestedScrollingEnabled()
        LogUtil.e("AAAA", "$TAG ;; isNestedScrollingEnabled :: isNestedScrollingEnabled = $isNestedScrollingEnabled")
        return isEnabled
    }

    override fun startNestedScroll(axes: Int): Boolean {
        val start = super.startNestedScroll(axes)
        LogUtil.e("AAAA", "$TAG ;; startNestedScroll :: start = $start ;; axes = $axes")
        return start
    }

    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        val start = super.onStartNestedScroll(child, target, nestedScrollAxes)
        LogUtil.e("AAAA", "$TAG ;; onStartNestedScroll :: start = $start ;; target = $target")
        return start
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        val start = super.onStartNestedScroll(child, target, axes, type)
        LogUtil.e("AAAA", "$TAG ;; onStartNestedScroll :: start = $start ;; axes = $axes ;; type = $type ;; target = $target")
        return start
    }

    override fun onNestedScrollAccepted(child: View, target: View, nestedScrollAxes: Int) {
        super.onNestedScrollAccepted(child, target, nestedScrollAxes)
        LogUtil.e("AAAA", "$TAG ;; onNestedScrollAccepted :: nestedScrollAxes = $nestedScrollAxes ;; target = $target")
    }

    override fun onNestedScrollAccepted(child: View, target: View, nestedScrollAxes: Int, type: Int) {
        super.onNestedScrollAccepted(child, target, nestedScrollAxes, type)
        LogUtil.e("AAAA", "$TAG ;; onNestedScrollAccepted :: nestedScrollAxes = $nestedScrollAxes ;; type = $type ;; target = $target")
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        val dispatch = super.dispatchNestedPreFling(velocityX, velocityY)
        LogUtil.e("AAAA", "$TAG ;; dispatchNestedPreFling :: velocityY = $velocityY ;; dispatch = $dispatch")
        return dispatch
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        val dispatch = super.dispatchNestedFling(velocityX, velocityY, consumed)
        LogUtil.e("AAAA", "$TAG ;; dispatchNestedFling :: velocityY = $velocityY ;; consumed = $consumed ;; dispatch = $dispatch")
        return dispatch
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
        val dispatch = super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
        LogUtil.e("AAAA", "$TAG ;; dispatchNestedPreScroll :: dy = $dy ;; consumed = $consumed ;; offsetInWindow = $offsetInWindow ;; dispatch = $dispatch")
        return dispatch
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        val dispatch = super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
        LogUtil.e("AAAA", "$TAG ;; dispatchNestedScroll :: dy = $dyConsumed ;; dyUnconsumed = $dyUnconsumed ;; offsetInWindow = $offsetInWindow ;; dispatch = $dispatch")
        return dispatch
    }

    override fun hasNestedScrollingParent(): Boolean {
        val has = super.hasNestedScrollingParent()
        LogUtil.e("AAAA", "$TAG ;; hasNestedScrollingParent :: has = $has")
        return has
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        LogUtil.e("AAAA", "$TAG ;; setNestedScrollingEnabled :: enabled = $enabled")
        super.setNestedScrollingEnabled(enabled)
    }

    override fun stopNestedScroll() {
        super.stopNestedScroll()
        LogUtil.e("AAAA", "$TAG ;; stopNestedScroll")
    }

    override fun onStopNestedScroll(target: View) {
        super.onStopNestedScroll(target)
        LogUtil.e("AAAA", "$TAG ;; onStopNestedScroll :: target = $target")
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        super.onStopNestedScroll(target, type)
        LogUtil.e("AAAA", "$TAG ;; onStopNestedScroll :: type = $type ;; target = $target")
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        LogUtil.e("AAAA", "$TAG ;; onNestedScroll :: dyConsumed = $dyConsumed ;; dyUnconsumed = $dyUnconsumed ;; target = $target")
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
        LogUtil.e("AAAA", "$TAG ;; onNestedScroll :: dyConsumed = $dyConsumed ;; dyUnconsumed = $dyUnconsumed ;; type = $type ;; target = $target")
    }

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int, consumed: IntArray) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed)
        LogUtil.e("AAAA", "$TAG ;; onNestedScroll :: dyConsumed = $dyConsumed ;; dyUnconsumed = $dyUnconsumed ;; type = $type ;; consumed = $consumed ;; target = $target")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        super.onNestedPreScroll(target, dx, dy, consumed)
        LogUtil.e("AAAA", "$TAG ;; onNestedPreScroll :: dy = $dy ;; consumed = $consumed ;; target = $target")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(target, dx, dy, consumed, type)
        LogUtil.e("AAAA", "$TAG ;; onNestedPreScroll :: dy = $dy ;; type = $type ;; consumed = $consumed ;; target = $target")
    }

    override fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        val fling = super.onNestedFling(target, velocityX, velocityY, consumed)
        LogUtil.e("AAAA", "$TAG ;; onNestedFling :: fling = $fling ;; y = $velocityY ;; consumed = $consumed ;; target = $target")
        return fling
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        val preFling = super.onNestedPreFling(target, velocityX, velocityY)
        LogUtil.e("AAAA", "$TAG ;; onNestedPreFling :: preFling = $preFling ;; y = $velocityY")
        return preFling
    }

    override fun getNestedScrollAxes(): Int {
        val type = super.getNestedScrollAxes()
        LogUtil.e("AAAA", "$TAG ;; getNestedScrollAxes :: type = $type")
        return type
    }

    override fun isPointInChildBounds(child: View, x: Int, y: Int): Boolean {
        val isPointInChild = super.isPointInChildBounds(child, x, y)
        LogUtil.e("AAAA", "$TAG ;; isPointInChildBounds :: y = $y ;; isPointInChild = $isPointInChild")
        return isPointInChild
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
}