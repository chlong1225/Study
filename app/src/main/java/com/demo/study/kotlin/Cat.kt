package com.demo.study.kotlin

import com.chl.common.utils.LogUtil

/**
 * Created by chl on 2021/9/19.
 * description :
 */
class Cat : Animal() {

    override fun eat() {
        LogUtil.e("AAAA","cat :: eat")
    }

}