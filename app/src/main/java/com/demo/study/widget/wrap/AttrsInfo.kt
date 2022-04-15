package com.stl.huikao.uilib.widget.wrap

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.annotation.Keep

/**
 * create on 2022/4/13
 * @author chenglong
 * description : 对应shape的属性值
 */
@Keep
class AttrsInfo {

    var shape = GradientDrawable.RECTANGLE
    var solidColor = Color.TRANSPARENT
    var strokeColor = Color.TRANSPARENT
    var strokeWidth = 0
    var allRadius = 0
    var topLeftRadius = 0
    var topRightRadius = 0
    var bottomLeftRadius = 0
    var bottomRightRadius = 0
}