package com.stl.commonlib.net

import androidx.annotation.Keep
import java.util.*
import kotlin.collections.ArrayList

/**
 * create on 12/17/21
 * @author chenglong
 * description : 统一收集网络请求需要的参数
 */
@Keep
class ParameterInfo(val url: String, val httpMethodType: Int) {

    //请求头参数
    private val headers: MutableMap<String, String> = HashMap()

    //post，put请求类型对应的请求体参数
    var body: String = ""

    //get，delete请求类型对应的请求参数，可以直接在url后进行拼接
    private val queryParams: MutableMap<String, String> = HashMap()

    //上传的文件路径
    private val filePaths: MutableList<String> = ArrayList()

    fun getHeaders(): Map<String, String> {
        return headers
    }

    fun addHeader(head: String, value: String) {
        headers[head] = value
    }

    fun getQueryParams(): Map<String, String> {
        return queryParams
    }

    fun addQueryParam(query: String, value: String) {
        queryParams[query] = value
    }

    fun addFilePath(filePath: String) {
        filePaths.add(filePath)
    }

    fun getFilePaths(): List<String> {
        return filePaths
    }
}