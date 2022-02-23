package com.demo.study

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.demo.study.databinding.ActivityStatusBarBinding

/**
 * create on 2022/2/22
 * @author chenglong
 * description : 验证沉浸式状态栏的实现效果
 * https://mp.weixin.qq.com/s/AiCNJAi9CgYDE1UuzCboGg
 */
class StatusBarActivity : BaseActivity<ActivityStatusBarBinding>() {

    companion object{

        fun openSelf(context: Context) {
            val intent = Intent(context, StatusBarActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun buildViewBinding(): ActivityStatusBarBinding {
        return ActivityStatusBarBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.TRANSPARENT

        getViewBinding().root.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        ViewCompat.setOnApplyWindowInsetsListener(getViewBinding().btnCheck
        ) { v, insets ->
            val params = v.layoutParams as FrameLayout.LayoutParams
            params.topMargin = insets.systemWindowInsetTop
            insets
        }
    }

}