package com.stl.huikao.uilib.widget.wrap

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * create on 2022/4/12
 * @author chenglong
 * description : 扩展ConstraintLayout，支持shape背景
 */
class BLConstraintLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }
        val bgDrawable = ParseAttrSetHelp.parseConstraintLayout(context, attrs)
        if (bgDrawable != null) {
            background = bgDrawable
        }
    }
}