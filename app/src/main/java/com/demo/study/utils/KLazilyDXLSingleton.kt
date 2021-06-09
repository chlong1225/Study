package com.demo.study.utils

import java.io.Serializable

/**
 * create by chenglong on 6/9/21
 * description : 单例设计模式
 */
class KLazilyDXLSingleton private constructor(): Serializable {

    fun doTest(){
        LogUtil.e("KLazilyDXLSingleton","test")
    }

    private fun readResolve():Any{
        return instance
    }

    companion object{

        val instance : KLazilyDXLSingleton by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            KLazilyDXLSingleton()
        }
    }

}