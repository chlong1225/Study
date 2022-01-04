package com.chl.common.utils

import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by chl on 2021/9/20.
 * description : 时间转换
 */
object TimeUtil {

    /**
     * 时间戳转换为格式化的时间
     */
    fun longToStr(time: Long, format: String): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        return simpleDateFormat.format(time)
    }

    fun strToLong(time: String, format: String): Long {
        val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        val parse = simpleDateFormat.parse(time, ParsePosition((0)))
        return parse!!.time
    }
}