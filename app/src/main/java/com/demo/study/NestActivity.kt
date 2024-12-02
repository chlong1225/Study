package com.demo.study

import android.content.Context
import android.content.Intent
import com.demo.study.databinding.ActivityNestBinding

/**
 * create on 2024/11/21
 * @author chenglong
 * description :
 */
class NestActivity : BaseActivity<ActivityNestBinding>() {

    companion object{

        fun openSelf(ctx: Context) {
            val intent = Intent(ctx, NestActivity::class.java)
            ctx.startActivity(intent)
        }
    }

    override fun buildViewBinding(): ActivityNestBinding {
        return ActivityNestBinding.inflate(layoutInflater)
    }
}