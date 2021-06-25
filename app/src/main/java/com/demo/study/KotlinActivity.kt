package com.demo.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.demo.study.databinding.ActivityKotlinBinding

/**
 * create by chenglong on 6/7/21
 * description :
 */
class KotlinActivity : BaseActivity<ActivityKotlinBinding>() {

    companion object {

        fun openSelf(context: Context) {
            context.startActivity(Intent(context, KotlinActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun buildViewBinding(): ActivityKotlinBinding {
        return ActivityKotlinBinding.inflate(layoutInflater)
    }

}