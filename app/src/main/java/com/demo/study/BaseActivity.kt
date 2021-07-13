package com.demo.study

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.chl.common.utils.LogUtil

/**
 * create by chenglong on 6/7/21
 * description :
 */
//生命周期变化场景：打开页面，返回键，Home键，横竖屏切换，弹窗dialog，AlertDialog，下拉通知栏
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var mViewBinding: VB

    companion object {
        const val TAG = "BaseActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var msg = "NULL"
        if (savedInstanceState != null) {
            msg = savedInstanceState.toString()
        }
        LogUtil.w(TAG, "onCreate: " + this.javaClass.name + " ;; " + msg)
        LogUtil.e(TAG, "onCreate: " + this.javaClass.name)
        mViewBinding = buildViewBinding();
        setContentView(mViewBinding.root)
    }

    abstract fun buildViewBinding(): VB

    override fun onStart() {
        super.onStart()
        LogUtil.e(TAG, "onStart: " + this.javaClass.name)

    }

    override fun onRestart() {
        super.onRestart()
        LogUtil.e(TAG, "onRestart: " + this.javaClass.name)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtil.w(TAG, "onSaveInstanceState: " + this.javaClass.name + " ;; " + outState.toString())
        LogUtil.e(TAG, "onSaveInstanceState: " + this.javaClass.name)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        LogUtil.w(TAG, "onRestoreInstanceState: " + this.javaClass.name + " ;; " + savedInstanceState.toString())
        LogUtil.e(TAG, "onRestoreInstanceState: " + this.javaClass.name)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LogUtil.w(TAG, "onConfigurationChanged: " + this.javaClass.name + " ;; " + newConfig.toString())
        LogUtil.e(TAG, "onConfigurationChanged: " + this.javaClass.name)
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