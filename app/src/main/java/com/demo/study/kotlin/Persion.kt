package com.demo.study.kotlin

/**
 * Created by chl on 2021/8/23.
 */
class Persion {

    var name = ""
        set(value) {
            field = "setName = " + value
        }
        get() = "getName = " + field
}