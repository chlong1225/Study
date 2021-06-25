package com.demo.study

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.chl.common.utils.LogUtil
import com.demo.study.databinding.ActivityMainBinding

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
            LogUtil.e("AAAA","onClick : set")
            SetActivity.openSelf(this@MainActivity)
        }
        getViewBinding().btnData.setOnClickListener({ v: View -> DataActivity.openSelf(this@MainActivity) })
    }

    override fun buildViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}