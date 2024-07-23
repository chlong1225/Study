package com.chl.common.net

/**
 * create on 12/17/21
 * @author chenglong
 * description : 网络请求方式
 */
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class HttpMethodType {

    companion object {

        /**
         * 分别对应get请求，post请求，put请求，delete请求，file上传文件
         */
        const val GET = 1
        const val POST = 2
        const val PUT = 3
        const val DELETE = 4
        const val FILE = 5
    }
}