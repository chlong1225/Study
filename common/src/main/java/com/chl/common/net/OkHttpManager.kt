package com.stl.commonlib.net

import android.os.Handler
import android.os.Looper
import com.chl.common.utils.LogUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

/**
 * create on 12/22/21
 * @author chenglong
 * description : okHttp请求操作类
 */
class OkHttpManager {

    private lateinit var mOkHttpClient: OkHttpClient

    private val mHandler = Handler(Looper.getMainLooper())

    companion object {

        val TAG = "OkHttpManager"

        @JvmStatic
        val instance: OkHttpManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpManager()
        }
    }

    fun init(isDebug: Boolean) {
        //默认read，write，connect的Timeout均设置10s
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                LogUtil.w(TAG, message)
            }
        })
        logging.level = HttpLoggingInterceptor.Level.BODY
        val build = OkHttpClient.Builder().addInterceptor(logging)
        if (isDebug) {
            build.sslSocketFactory(CertificateManager.createSSLSocketFactory(), CertificateManager.TrustAllManager())
        }
        mOkHttpClient = build.build()
    }

    fun httpRequest(parameterInfo: ParameterInfo, callBack: CallBack) {

        val request = RequestFactory.buildRequest(parameterInfo)
        mOkHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                LogUtil.w(TAG, "onFailure = ${e.message}")
                mHandler.post {
                    callBack.onError(-1, e.message)
                    callBack.onCompleted()
                }
            }

            override fun onResponse(call: Call, response: Response) {
//                val body = response.body!!.string()
//                val model = BLJsonUtils.fromJson(body, ReturnModel::class.java)
                mHandler.post {
//                    callBack.onSuccess(model)
                    callBack.onCompleted()
                }
            }
        })
    }
}