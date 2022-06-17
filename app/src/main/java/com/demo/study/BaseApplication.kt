package com.demo.study

import android.app.Application
import com.chl.common.utils.LogUtil

/**
 * create on 2022/6/6
 * @author chenglong
 * description :
 */
class BaseApplication() : Application() {

    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            LogUtil.e("AAAA", "thread = " + t.name + " ;; msg = " + e.message + " ;; " + e.toString())
        }
    }
}