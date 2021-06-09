package com.demo.study

import android.os.Bundle
import android.widget.Button
import com.demo.study.databinding.ActivityMainBinding
import com.demo.study.utils.LogUtil

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var mKotlin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mKotlin = findViewById(R.id.btn_kotlin);
        /*mKotlin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                KotlinActivity.openSelf(this@MainActivity)
            }
        })*/
        mKotlin.setOnClickListener {
            LogUtil.e("AAAA","onClick")
            KotlinActivity.openSelf(this@MainActivity)
        }
        /*mKotlin.setOnClickListener({ v: View -> KotlinActivity.openSelf(this@MainActivity) })*/
    }
}