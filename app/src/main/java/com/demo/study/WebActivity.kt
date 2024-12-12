package com.demo.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.demo.study.databinding.ActivityWebBinding

/**
 * create on 2024/12/12
 * @author chenglong
 * description :
 */
class WebActivity : BaseActivity<ActivityWebBinding>() {

    companion object {

        fun openSelf(ctx: Context) {
            val intent = Intent(ctx, WebActivity::class.java)
            ctx.startActivity(intent)
        }
    }

    override fun buildViewBinding(): ActivityWebBinding {
        return ActivityWebBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webSettings = getViewBinding().wbView.settings
        webSettings.javaScriptEnabled = true
        getViewBinding().wbView.webViewClient = WebViewClient()
        getViewBinding().wbView.webChromeClient = WebChromeClient()

        getViewBinding().wbView.loadUrl("file:///android_asset/tronweb_example.html")
    }

}