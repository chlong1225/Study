package com.chl.common.net

/**
 * create on 12/20/21
 * @author chenglong
 * description : 接口请求结果的回调接口
 */
interface CallBack {

    fun onSuccess(body: String)

    fun onError(errorCode: Int, errorMsg: String?)

    fun onCompleted()
}