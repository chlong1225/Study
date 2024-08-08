package com.demo.study

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewGroup
import com.chl.common.utils.LogUtil
import com.chl.common.utils.ScreenUtil
import com.demo.study.databinding.ActivityFloatBinding

/**
 * create on 2024/7/26
 * @author chenglong
 * description :
 */
class FloatActivity : BaseActivity<ActivityFloatBinding>() {

    companion object{

        fun openSelf(ctx: Context) {
            val intent = Intent(ctx, FloatActivity::class.java)
            ctx.startActivity(intent)
        }
    }

    private var isSmall = true
    private var startX = 0f
    private var startY = 0f
    private var scaleX = 0.6f
    private var scaleY = 0.6f

    override fun buildViewBinding(): ActivityFloatBinding {
        return ActivityFloatBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val decorView = window.decorView as ViewGroup
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        decorView.setBackgroundColor(Color.TRANSPARENT)
        val root = getViewBinding().clRoot
        val group = root.parent as ViewGroup
        group.removeView(root)
        decorView.removeAllViews()
        decorView.addView(getViewBinding().clRoot, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        updateWindow()

        getViewBinding().btnSmall.setOnClickListener {
            if (isSmall) {
                return@setOnClickListener
            }
            isSmall = true
            updateWindow()
        }
        getViewBinding().btnReset.setOnClickListener {
            if (isSmall) {
                isSmall = false
                updateWindow()
            }
        }



        getViewBinding().ivMove.setOnTouchListener { v, event ->
            if (isSmall) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    startX = event.rawX
                    startY = event.rawY
                }else if (event.action == MotionEvent.ACTION_MOVE) {
                    val dx = event.rawX - startX
                    val dy = event.rawY - startY
                    getViewBinding().clRoot.translationX += dx
                    getViewBinding().clRoot.translationY += dy
                    startX = event.rawX
                    startY = event.rawY
                } else {
                    val location = IntArray(2)
                    getViewBinding().clRoot.getLocationOnScreen(location)
                    if (location[0] < 0) {
                        getViewBinding().clRoot.translationX = getViewBinding().clRoot.width * scaleX / 2 - ScreenUtil.getScreenWidth() / 2
                    }else if (location[0] + getViewBinding().clRoot.width * scaleX > ScreenUtil.getScreenWidth()) {
                        getViewBinding().clRoot.translationX = ScreenUtil.getScreenWidth() / 2 - getViewBinding().clRoot.width * scaleX / 2
                    }
                    if (location[1] < ScreenUtil.getStatusBarHeight()) {
                        getViewBinding().clRoot.translationY =
                            getViewBinding().clRoot.height * scaleY / 2 - ScreenUtil.getScreenHeight() / 2 + ScreenUtil.getStatusBarHeight() / 2
                    }else if (location[1] + getViewBinding().clRoot.height * scaleY > ScreenUtil.getScreenHeight() + ScreenUtil.getStatusBarHeight()) {
                        getViewBinding().clRoot.translationY =
                            ScreenUtil.getScreenHeight() / 2 - getViewBinding().clRoot.height * scaleY / 2 + ScreenUtil.getStatusBarHeight() / 2
                    }
                    val width = getViewBinding().clRoot.measuredWidth
                    val height = getViewBinding().clRoot.measuredHeight
                    LogUtil.e("AAAA","w = $width ;; h = $height")
                }
                return@setOnTouchListener true
            } else {
                return@setOnTouchListener false
            }
        }
    }

    private var isValid = false

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (isSmall) {
            if (ev.action == MotionEvent.ACTION_DOWN) {
                isValid = isTouchOutSide(ev)
            }
            LogUtil.e("AAAA", "isOutSide = $isValid")
            if (isValid) {
                WindowManager.window?.injectInputEvent(MotionEvent.obtain(ev))
                window.currentFocus?.clearFocus()
                WindowManager.window?.setLocalFocus(true, true)
                return false
            }
        }
        window.setLocalFocus(true,true)
        WindowManager.window?.currentFocus?.clearFocus()
        return super.dispatchTouchEvent(ev)
    }

    private fun isTouchOutSide(ev: MotionEvent): Boolean {
        val x = ev.rawX
        val y = ev.rawY
        val location = IntArray(2)
        getViewBinding().clRoot.getLocationOnScreen(location)
        val left = location[0]
        val top = location[1]
        val right = left + getViewBinding().clRoot.width * scaleX
        val bottom = top + getViewBinding().clRoot.height * scaleY
        return x < left || x > right || y < top || y > bottom
    }

    private fun updateWindow() {
        if (isSmall) {
            getViewBinding().clRoot.scaleX = scaleX
            getViewBinding().clRoot.scaleY = scaleY
        } else {
            getViewBinding().clRoot.scaleX = 1f
            getViewBinding().clRoot.scaleY = 1f
            getViewBinding().clRoot.translationX = 0f
            getViewBinding().clRoot.translationY = 0f
        }
    }
}