package com.chl.common.net

import com.chl.common.net.request.DeleteRequest
import com.chl.common.net.request.FileRequest
import com.chl.common.net.request.GetRequest
import com.chl.common.net.request.PostRequest
import com.chl.common.net.request.PutRequest
import okhttp3.Request
import java.lang.IllegalArgumentException

/**
 * create on 12/22/21
 * @author chenglong
 * description : 用于将网络请求参数ParameterInfo转换为okHttp请求的Request参数
 */
class RequestFactory {

    companion object {

        fun buildRequest(parameterInfo: ParameterInfo): Request {
            when (parameterInfo.httpMethodType) {
                HttpMethodType.GET -> return GetRequest().buildRequest(parameterInfo)
                HttpMethodType.POST -> return PostRequest().buildRequest(parameterInfo)
                HttpMethodType.PUT -> return PutRequest().buildRequest(parameterInfo)
                HttpMethodType.DELETE -> return DeleteRequest().buildRequest(parameterInfo)
                HttpMethodType.FILE -> return FileRequest().buildRequest(parameterInfo)
            }
            throw IllegalArgumentException("不支持的请求类型${parameterInfo.httpMethodType}，请扩展")
        }
    }
}