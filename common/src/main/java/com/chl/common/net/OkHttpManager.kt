package com.chl.common.net

import android.os.Handler
import android.os.Looper
import com.chl.common.json.BLJsonUtils
import com.chl.common.utils.LogUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

/**
 * create on 12/22/21
 * @author chenglong
 * description : okHttp请求操作类
 */
class OkHttpManager {

    private lateinit var mOkHttpClient: OkHttpClient

    private val mHandler = Handler(Looper.getMainLooper())

    private val buf: ByteArray = ByteArray(16 * 1024)

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
                val body = response.body!!.string()
                val model = BLJsonUtils.fromJson(body, ReturnModel::class.java)
                mHandler.post {
                    callBack.onSuccess(model)
                    callBack.onCompleted()
                }
            }
        })
    }

    /**
     * 下载文件
     */
    fun downloadFile(url: String, filePath: String, downloadFileListener: DownloadFileListener) {
        val request = Request.Builder()
            .url(url)
            .build()
        mOkHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                mHandler.post {
                    downloadFileListener.onDownloadFail(e.message)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val file = File(filePath)
                    if (response.body == null) {
                        downloadFileListener.onDownloadFail(response.message)
                    } else {
                        response.body?.let {
                            var byteStream: InputStream? = null
                            var fos:FileOutputStream? = null
                            try {
                                byteStream = it.byteStream()
                                fos = FileOutputStream(file)
                                val total = it.contentLength()
                                var len = 0
                                var sum = 0L
                                while (true) {
                                    len = byteStream.read(buf)
                                    if (len == -1) {
                                        break
                                    }
                                    fos.write(buf, 0, len)
                                    sum += len
                                    val progress = sum * 1.0 / total
                                    mHandler.post {
                                        downloadFileListener.onDownloadProgress(progress)
                                    }
                                }
                                fos.flush()
                                mHandler.post {
                                    downloadFileListener.onDownloadSuccess(filePath)
                                }
                            } catch (e: Exception) {
                                mHandler.post {
                                    downloadFileListener.onDownloadFail(e.message)
                                }
                            }finally {
                                byteStream?.close()
                                fos?.close()
                            }
                        }
                    }
                } else {
                    downloadFileListener.onDownloadFail(response.message)
                }
            }
        })
    }
}