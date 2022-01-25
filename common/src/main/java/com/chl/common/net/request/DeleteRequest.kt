package com.stl.huikao.uilib.utils.net.request

import com.stl.commonlib.net.ParameterInfo
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request

/**
 * create on 12/22/21
 * @author chenglong
 * description : 对应delete类型的请求
 */
class DeleteRequest : IRequest {

    override fun buildRequest(parameterInfo: ParameterInfo): Request {
        val build = Request.Builder()
        //添加请求头
        parameterInfo.getHeaders().keys.forEach {
            build.addHeader(it, parameterInfo.getHeaders()[it].toString())
        }
        val urlBuild = parameterInfo.url.toHttpUrl().newBuilder()
        //添加请求参数
        parameterInfo.getQueryParams().keys.forEach {
            urlBuild.addQueryParameter(it, parameterInfo.getQueryParams()[it])
        }
        build.url(urlBuild.build())
        build.delete(null)
        return build.build()
    }
}