package com.demo.study.exp

/**
 * create on 2023/10/26
 * @author chenglong
 * description : String类的扩展函数
 */
fun String.trimAll(): String {
    if (this.isEmpty()) {
        return this
    }
    val build = StringBuilder()
    for (i in this.indices) {
        if (this[i] != ' ') {
            build.append(this[i])
        }
    }
    return build.toString()
}