package com.demo.study.kotlin

import com.chl.common.utils.LogUtil

/**
 * Created by chl on 2021/9/19.
 * description :
 */
open class Animal {

    open fun eat() {
        LogUtil.e("AAAA", "animal :: eat")
    }
}