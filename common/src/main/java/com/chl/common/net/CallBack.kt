package com.stl.commonlib.net

import com.stl.commonlib.net.ReturnModel

/**
 * create on 12/20/21
 * @author chenglong
 * description : 接口请求结果的回调接口
 */
interface CallBack {

    fun onSuccess(returnModel: ReturnModel)

    fun onError(errorCode: Int, errorMsg: String?)

    fun onCompleted()
}