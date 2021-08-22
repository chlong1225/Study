package com.chl.common.utils

import android.util.Log

/**
 * create by chenglong on 6/7/21
 * description : 使用装饰设计模式包装日志打印
 */
object LogUtil {

    @JvmField
    var isDebug = true

    @JvmStatic
    fun d(tag: String, msg: String) {
        if (isDebug) {
            Log.d(tag, msg)
        }
    }

    @JvmStatic
    fun i(tag: String, msg: String) {
        if (isDebug) {
            Log.i(tag, msg)
        }
    }

    @JvmStatic
    fun w(tag: String, msg: String) {
        if (isDebug) {
            Log.w(tag, msg)
        }
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        if (isDebug) {
            Log.e(tag, msg)
        }
    }
}