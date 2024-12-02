package com.demo.study.widget.nest

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.chl.common.utils.LogUtil
import com.google.android.material.appbar.AppBarLayout

/**
 * create on 2024/11/19
 * @author chenglong
 * description :
 */
class MyBehavior(context: Context?, attrs: AttributeSet? = null) : AppBarLayout.Behavior(context, attrs) {

    companion object{

        private const val TAG = "MyBehavior"
    }

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: AppBarLayout, ev: MotionEvent): Boolean {
        val b = super.onInterceptTouchEvent(parent, child, ev)
        LogUtil.e("AAAA", "$TAG ;; onInterceptTouchEvent :: action = ${ev.action} ;; y = ${ev.y} ;; b = $b")
        return b
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: AppBarLayout, ev: MotionEvent): Boolean {
        val b = super.onTouchEvent(parent, child, ev)
        LogUtil.e("AAAA", "$TAG ;; onTouchEvent :: action = ${ev.action} ;; y = ${ev.y} ;; b = $b")
        return b
    }

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean {
        val b = super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type)
        LogUtil.e("AAAA", "$TAG ;; onStartNestedScroll :: nestedScrollAxes = $nestedScrollAxes ;; type = $type ;; b = $b ;; view = $target")
        return b
    }

    override fun onNestedPreFling(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        val b = super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
        LogUtil.e("AAAA", "$TAG ;; onNestedPreFling :: velocityY = $velocityY ;; b = $b ;; view = $target")
        return b
    }

    override fun onNestedFling(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        val b = super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
        LogUtil.e("AAAA", "$TAG ;; onNestedFling :: velocityY = $velocityY ;; consumed = $consumed ;; b = $b ;; view = $target")
        return b
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        LogUtil.e("AAAA", "$TAG ;; onNestedPreScroll :: dy = $dy ;; consumed = $consumed ;; type = $type ;; view = $target")
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed)
        LogUtil.e("AAAA", "$TAG ;; onNestedScroll :: dyConsumed = $dyConsumed ;; dyUnconsumed = $dyUnconsumed ;; consumed = $consumed ;; type = $type ;; view = $target")
    }

    override fun onNestedScrollAccepted(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type)
        LogUtil.e("AAAA", "$TAG ;; onNestedScrollAccepted :: axes = $axes ;; type = $type ;; view = $target")
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, abl: AppBarLayout, target: View, type: Int) {
        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
        LogUtil.e("AAAA", "$TAG ;; onStopNestedScroll :: type = $type ;; view = $target")
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed)
        LogUtil.e("AAAA", "$TAG ;; onNestedPreScroll :: dy = $dy ;; consumed = $consumed ;; view = $target")
    }

    override fun onNestedScrollAccepted(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, axes: Int) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes)
        LogUtil.e("AAAA", "$TAG ;; onNestedScrollAccepted :: axes = $axes ;; view = $target")
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        LogUtil.e("AAAA", "$TAG ;; onNestedScroll :: dyConsumed = $dyConsumed ;; dyUnconsumed = $dyUnconsumed ;; view = $target")
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
        LogUtil.e("AAAA", "$TAG ;; onNestedScroll :: dyConsumed = $dyConsumed ;; dyUnconsumed = $dyUnconsumed ;; type = $type ;; view = $target")
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        axes: Int
    ): Boolean {
        val b = super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes)
        LogUtil.e("AAAA", "$TAG ;; onStartNestedScroll :: axes = $axes ;; view = $target")
        return b
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View) {
        super.onStopNestedScroll(coordinatorLayout, child, target)
        LogUtil.e("AAAA", "$TAG ;; onStopNestedScroll :: view = $target")
    }
}