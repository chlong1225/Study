package com.stl.huikao.uilib.utils.net.request

import com.stl.commonlib.net.ParameterInfo
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

/**
 * create on 12/22/21
 * @author chenglong
 * description : 对应上传文件的请求
 */
class FileRequest : IRequest {

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
        val bodyBuild = MultipartBody.Builder().setType(MultipartBody.FORM)
        parameterInfo.getFilePaths().forEach {
            val file = File(it)
            bodyBuild.addFormDataPart("file", file.name, file.asRequestBody("multipart/form-data".toMediaType()))
        }
        build.post(bodyBuild.build())
        return build.build()
    }
}