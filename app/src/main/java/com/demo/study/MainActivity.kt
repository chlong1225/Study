package com.demo.study

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.chl.common.utils.LogUtil
import com.demo.study.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var mKotlin: Button
    lateinit var mSet: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mKotlin = findViewById(R.id.btn_kotlin);
        mSet = findViewById(R.id.btn_set)
        mKotlin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                LogUtil.e("AAAA","onClick : kotlin")
                KotlinActivity.openSelf(this@MainActivity)
            }
        })
        mSet.setOnClickListener {
            LogUtil.e("AAAA","onClick : set")
            SetActivity.openSelf(this@MainActivity)
        }
//        mKotlin.setOnClickListener({ v: View -> KotlinActivity.openSelf(this@MainActivity) })
    }
}