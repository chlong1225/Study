package com.chl.common.utils

/**
 * create on 2024/7/29
 * @author chenglong
 * description : 扩展函数
 */
fun Float.dp2px(): Int {
    return ScreenUtil.dp2px(this)
}

fun Float.sp2px(): Int {
    return ScreenUtil.sp2px(this)
}

fun Int.px2dp(): Float {
    return ScreenUtil.px2dp(this)
}

fun Int.px2sp():Float{
    return ScreenUtil.px2sp(this)
}