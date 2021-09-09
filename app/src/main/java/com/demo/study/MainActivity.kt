package com.demo.study

import android.os.Bundle
import android.view.View
import com.chl.common.utils.LogUtil
import com.demo.study.databinding.ActivityMainBinding
import com.demo.study.kotlin.*

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewBinding().btnKotlin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                LogUtil.e("AAAA","onClick : kotlin")
                KotlinActivity.openSelf(this@MainActivity)
            }
        })
        getViewBinding().btnSet.setOnClickListener {
            LogUtil.e("AAAA","onClick : set : $it")
            SetActivity.openSelf(this@MainActivity)
        }
        getViewBinding().btnData.setOnClickListener({ DataActivity.openSelf(this@MainActivity) })

        test()
    }

    private fun test() {
        val inlineTest1 = InlineTest1(Persion("AAAA"))
        val inlineTest2 = InlineTest2(10);
        val test1 = Test1(Persion("bbbb"))
        val test2 = Test2(20)
    }

    override fun buildViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}