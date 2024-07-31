package com.demo.study

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
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

    override fun buildViewBinding(): ActivityFloatBinding {
        return ActivityFloatBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        getViewBinding().root.setOnTouchListener { v, event ->
            if (isSmall) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    startX = event.rawX
                    startY = event.rawY
                }else if (event.action == MotionEvent.ACTION_MOVE) {
                    val dx = event.rawX - startX
                    val dy = event.rawY - startY
                    val params = window.attributes
                    params.x += dx.toInt()
                    params.y += dy.toInt()
                    window.attributes = params
                    startX = event.rawX
                    startY = event.rawY
                }
                return@setOnTouchListener true
            } else {
                return@setOnTouchListener false
            }
        }
    }

    private fun updateWindow() {
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.decorView.background = null
        if (isSmall) {
            val params = window.attributes
            params.width = ScreenUtil.getScreenWidth() / 2
            params.height = ScreenUtil.getScreenHeight() / 2
            params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
            window.attributes = params
            window.setDimAmount(0f)
        } else {
            val params = window.attributes
            params.width = ScreenUtil.getScreenWidth()
            params.height = ScreenUtil.getScreenHeight()
            params.x = 0
            params.y = 0
            window.attributes = params
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
            window.setDimAmount(1f)
        }
    }
}