package com.stl.huikao.uilib.widget.wrap

import android.R
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * create on 2022/4/11
 * @author chenglong
 * description : 扩展TextView，支持shape背景
 */
open class BLTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.textViewStyle) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }
        val bgDrawable = ParseAttrSetHelp.parseTextView(context, attrs)
        if (bgDrawable != null) {
            background = bgDrawable
        }
    }
}