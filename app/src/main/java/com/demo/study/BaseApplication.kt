package com.demo.study

import android.app.Application
import com.chl.common.utils.LogUtil
import com.chl.common.utils.ScreenUtil

/**
 * create on 2022/6/6
 * @author chenglong
 * description :
 */
class BaseApplication() : Application() {

    override fun onCreate() {
        super.onCreate()

        ScreenUtil.init(this)

        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            LogUtil.e("AAAA", "thread = " + t.name + " ;; msg = " + e.message + " ;; " + e.toString())
        }
    }
}