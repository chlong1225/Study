package com.demo.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.chl.common.utils.LogUtil
import com.demo.study.databinding.ActivityKotlinBinding

/**
 * create by chenglong on 6/7/21
 * description :
 */
class KotlinActivity : BaseActivity<ActivityKotlinBinding>() {

    companion object {

        val TAG = "KotlinActivity"

        fun openSelf(context: Context) {
            context.startActivity(Intent(context, KotlinActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        test()
    }

    private fun test() {
        LogUtil.e(TAG, "位运算左移 = " + (10 shl 2))
        LogUtil.e(TAG, "位运算右移 = " + (10 shr 2))
        LogUtil.e(TAG, "位运算无符号右移 = " + (10 ushr 2))
        LogUtil.e(TAG, "位运算与 = " + (10 and 2))
        LogUtil.e(TAG, "位运算或 = " + (10 or 2))
        LogUtil.e(TAG, "位运算异或 = " + (10 xor  2))
        LogUtil.e(TAG, "位运算非 = " + (10.inv()))

    }

    override fun buildViewBinding(): ActivityKotlinBinding {
        return ActivityKotlinBinding.inflate(layoutInflater)
    }

}