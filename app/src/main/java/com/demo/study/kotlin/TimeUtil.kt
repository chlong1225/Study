package com.demo.study.kotlin

import java.text.SimpleDateFormat

/**
 * Created by chl on 2021/8/29.
 */
object  TimeUtil {

    fun longToStr(longTime: Long): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return simpleDateFormat.format(longTime)
    }
}