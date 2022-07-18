package com.demo.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.chl.common.utils.LogUtil
import com.demo.study.databinding.ActivityLifecycleBinding

/**
 * create on 2022/7/16
 * @author chenglong
 * description : 用于验证Lifecycle
 */
class LifecycleActivity : BaseActivity<ActivityLifecycleBinding>() {

    companion object{

        const val TAG = "LifecycleActivity"

        fun openSelf(context: Context) {
            val intent = Intent(context, LifecycleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val observer = object:DefaultLifecycleObserver{

        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            LogUtil.e(TAG,"observer : onCreate ;11  ${owner.lifecycle.currentState} ;; ${owner.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)}")
            Handler(Looper.getMainLooper()).postDelayed(object : Runnable {

                override fun run() {
                    LogUtil.e(TAG,"observer : onCreate ; ${owner.lifecycle.currentState} ;; ${owner.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)}")
                }

            }, 0)
        }

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            LogUtil.e(TAG,"observer : onResume")
        }

        override fun onPause(owner: LifecycleOwner) {
            super.onPause(owner)
            LogUtil.e(TAG,"observer : onPause")
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            LogUtil.e(TAG,"observer : onDestroy")
        }
    }

    override fun buildViewBinding(): ActivityLifecycleBinding {
        return ActivityLifecycleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.e(TAG,"LifecycleActivity : onCreate")
        lifecycle.addObserver(observer)
    }

    override fun onResume() {
        super.onResume()
        LogUtil.e(TAG,"LifecycleActivity : onResume")
    }

    override fun onPause() {
        super.onPause()
        LogUtil.e(TAG,"LifecycleActivity : onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.e(TAG,"LifecycleActivity : onDestroy")
    }

}