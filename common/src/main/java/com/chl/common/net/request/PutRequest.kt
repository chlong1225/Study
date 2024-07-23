package com.chl.common.net.request

import com.chl.common.net.ParameterInfo
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * create on 12/22/21
 * @author chenglong
 * description : 对应put请求
 */
class PutRequest : IRequest {

    override fun buildRequest(parameterInfo: ParameterInfo): Request {
        val build = Request.Builder()
        //添加请求头
        parameterInfo.getHeaders().keys.forEach {
            build.addHeader(it, parameterInfo.getHeaders()[it].toString())
        }
        build.url(parameterInfo.url)
        build.put(parameterInfo.body.toRequestBody("application/json".toMediaType()))
        return build.build()
    }
}