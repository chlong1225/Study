package com.chl.common.net.request

import com.chl.common.net.ParameterInfo
import okhttp3.Request

/**
 * create on 12/22/21
 * @author chenglong
 * description : 用于定义构建Request
 */
interface IRequest {

    /**
     * 根据请求参数parameterInfo构建对应的request
     */
    fun buildRequest(parameterInfo: ParameterInfo): Request
}