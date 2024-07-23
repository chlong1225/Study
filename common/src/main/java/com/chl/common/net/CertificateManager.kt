package com.chl.common.net

import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * create on 12/27/21
 * @author chenglong
 * description : okHttp配置信任所有证书，便于抓包。正式环境发布时需要取消此配置
 */
class CertificateManager {

    companion object{

        fun createSSLSocketFactory(): SSLSocketFactory {
            val instance = SSLContext.getInstance("TLS")
            instance.init(null, arrayOf<TrustManager>(TrustAllManager()), SecureRandom())
            return instance.socketFactory
        }
    }

    class TrustAllManager : X509TrustManager {

        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate?> {
            return arrayOfNulls(0)
        }

    }
}