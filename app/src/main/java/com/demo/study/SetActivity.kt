package com.demo.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.demo.study.databinding.ActivitySetBinding

/**
 * create by chenglong on 6/16/21
 * description :
 */
class SetActivity : BaseActivity<ActivitySetBinding>() {

    companion object {

        fun openSelf(context: Context) {
            context.startActivity(Intent(context, SetActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun buildViewBinding(): ActivitySetBinding {
        return ActivitySetBinding.inflate(layoutInflater)
    }
}