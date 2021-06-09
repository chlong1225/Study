package com.demo.study.utils

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

/**
 * create by chenglong on 6/8/21
 * description :
 */
class ViewBindingHelp {

    inline fun <reified T : ViewBinding> Activity.inflat(){
        T::class.java.getMethod("inflate",LayoutInflater::class.java).invoke(null,layoutInflater) as T
    }
}