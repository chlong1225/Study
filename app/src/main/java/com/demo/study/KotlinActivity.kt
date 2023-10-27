package com.demo.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.chl.common.utils.LogUtil
import com.demo.study.databinding.ActivityKotlinBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.lang.NullPointerException

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

    override fun buildViewBinding(): ActivityKotlinBinding {
        return ActivityKotlinBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewBinding().btnStart.setOnClickListener {
            start()
            testCoroutineContext()
            testCoroutineStart()
//            testCoroutineExceptionHandler()
        }
    }

    private fun testCoroutineExceptionHandler() {
        val exceptionHandler = CoroutineExceptionHandler{coroutineContext, throwable ->
            LogUtil.e(TAG, "testCoroutineExceptionHandler : 33 : ${coroutineContext[CoroutineName]} ;; ${coroutineContext} ;; $throwable")
        }
        GlobalScope.launch(CoroutineName("异常处理") + exceptionHandler) {
            val job = launch {
                LogUtil.e(TAG, "testCoroutineExceptionHandler : 11 : thread = ${Thread.currentThread().name}")
                throw NullPointerException("测试异常")
            }
            job.join()
            LogUtil.e(TAG, "testCoroutineExceptionHandler : 22 : thread = ${Thread.currentThread().name}")
        }
    }

    private fun testCoroutineStart() {
        val defaultJob = GlobalScope.launch {
            LogUtil.e(TAG, "testCoroutineStart : thread = ${Thread.currentThread().name} ;; default")
        }
        defaultJob.cancel()
        val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY){
            LogUtil.e(TAG, "testCoroutineStart : thread = ${Thread.currentThread().name} ;; lazyJob")
        }
        val atomicJob = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
            LogUtil.e(TAG, "testCoroutineStart : thread = ${Thread.currentThread().name} ;; atomic before")
            delay(100)
            LogUtil.e(TAG, "testCoroutineStart : thread = ${Thread.currentThread().name} ;; atomic after")
        }
        atomicJob.cancel()
        val undJob = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
            LogUtil.e(TAG, "testCoroutineStart : thread = ${Thread.currentThread().name} ;; undispatched before")
            delay(100)
            LogUtil.e(TAG, "testCoroutineStart : thread = ${Thread.currentThread().name} ;; undispatched after")
        }
        undJob.cancel()

        GlobalScope.launch {
            LogUtil.e(TAG, "context : 父协程 = ${coroutineContext}")
            launch(CoroutineName("第一个子协程")) {
                LogUtil.e(TAG, "context :11 = $coroutineContext")
            }
            launch(CoroutineName("第二个子协程")) {
                LogUtil.e(TAG, "context :22 = $coroutineContext")
            }
        }
    }

    private fun testCoroutineContext() {
        val coroutineContext1 = Job() + CoroutineName("这是一个上下文")
        LogUtil.e(TAG, "testCoroutineContext : thread = ${Thread.currentThread().name} ;; coroutineContext1 = $coroutineContext1")
        val coroutineContext2 = coroutineContext1 + Dispatchers.Default + CoroutineName("这是第二个上下文")
        LogUtil.e(TAG, "testCoroutineContext : thread = ${Thread.currentThread().name} ;; coroutineContext2 = $coroutineContext2")
        val coroutineContext3 = coroutineContext2 + Dispatchers.Main + CoroutineName("这是第三个上下文")
        LogUtil.e(TAG, "testCoroutineContext : thread = ${Thread.currentThread().name} ;; coroutineContext3 = $coroutineContext3")
    }

    private fun start() {
        runBlocking {
            LogUtil.e(TAG, "启动一个协程 runBlocking ：thread = ${Thread.currentThread().name}")
        }
        LogUtil.e(TAG, "start : 11 = ${Thread.currentThread().name}")
        val launch = GlobalScope.launch() {
            LogUtil.e(TAG, "启动一个协程 ： GlobalScope : launch : thread = ${Thread.currentThread().name}")
        }
        LogUtil.e(TAG, "start :22 = ${Thread.currentThread().name} ;; ${launch}")
        val async = GlobalScope.async {
            LogUtil.e(TAG, "启动一个协程 ：GlobalScope ： async : thread = ${Thread.currentThread().name}")
        }
        LogUtil.e(TAG,"start : 33 = ${Thread.currentThread().name} ;; $async")

        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO){
                Thread.sleep(2000)
                "测试协程异步处理"
            }
            getViewBinding().tvTitle.text = result
        }
    }
}