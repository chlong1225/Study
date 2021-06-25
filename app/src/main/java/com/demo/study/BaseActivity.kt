package com.demo.study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.chl.common.utils.LogUtil

/**
 * create by chenglong on 6/7/21
 * description :
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var mViewBinding: VB

    companion object {
        const val TAG = "BaseActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.e(TAG, "onCreate: " + this.javaClass.name)
        mViewBinding = buildViewBinding();
        setContentView(mViewBinding.root)
    }

    abstract fun buildViewBinding(): VB

    override fun onStart() {
        super.onStart()
        LogUtil.e(TAG, "onStart: " + this.javaClass.name)

    }

    override fun onResume() {
        super.onResume()
        LogUtil.e(TAG, "onResume: " + this.javaClass.name)
    }

    override fun onPause() {
        super.onPause()
        LogUtil.e(TAG, "onPause: " + this.javaClass.name)
    }

    override fun onStop() {
        super.onStop()
        LogUtil.e(TAG, "onStop: " + this.javaClass.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.e(TAG, "onDestroy: " + this.javaClass.name)
    }

    fun getViewBinding():VB{
        return mViewBinding
    }
}