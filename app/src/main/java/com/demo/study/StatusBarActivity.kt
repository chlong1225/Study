package com.demo.study

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.demo.study.databinding.ActivityStatusBarBinding

/**
 * create on 2022/2/22
 * @author chenglong
 * description :
 */
class StatusBarActivity : BaseActivity<ActivityStatusBarBinding>() {

    companion object{

        fun openSelf(context: Context) {
            val intent = Intent(context, StatusBarActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun buildViewBinding(): ActivityStatusBarBinding {
        return ActivityStatusBarBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.TRANSPARENT
    }

}