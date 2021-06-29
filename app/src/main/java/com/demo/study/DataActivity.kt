package com.demo.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.chl.common.utils.LogUtil
import com.demo.study.data.AppDatabase
import com.demo.study.data.TokenBean
import com.demo.study.data.User
import com.demo.study.databinding.ActivityDataBinding

/**
 * create by chenglong on 6/25/21
 * description :
 */
class DataActivity : BaseActivity<ActivityDataBinding>() {

    companion object{

        const val TAG = "DataActivity"

        fun openSelf(context: Context) {
            context.startActivity(Intent(context, DataActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDao = AppDatabase.getInstance(this).userDao()
        val tokenDao = AppDatabase.getInstance(this).tokenDao()

        getViewBinding().btnInsert.setOnClickListener {
            userDao.add(User(120, "haha1", 20,"aa","测试1"))
            userDao.add(User(121, "haha2", 21,"bb","测试2"))
            userDao.add(User(122, "haha3", 20,"vv","测试3"))
            userDao.add(User(117, "haha4", 21,"cc","测试4"))
            userDao.add(User(116, "haha5", 19,"axa","测试5"))
            userDao.add(User(125, "haha6", 20,"aza","测试6"))
            userDao.add(User(124, "haha7", 20,"aaa","测试7"))

            val token1 = TokenBean()
            token1.productId = "25"
            token1.token = "abc1"
            tokenDao.add(token1)

            val token2 = TokenBean()
            token2.productId = "26"
            token2.token = "abc2"
            tokenDao.add(token2)

            val token3 = TokenBean()
            token3.productId = "27"
            token3.token = "abc3"
            tokenDao.add(token3)

            val token4 = TokenBean()
            token4.productId = "25"
            token4.token = "abc4"
            tokenDao.add(token4)

            val token5 = TokenBean()
            token5.productId = "26"
            token5.token = "abc5"
            tokenDao.add(token5)
        }
        getViewBinding().btnQueryAge.setOnClickListener {
            val datas = userDao.queryByAge(20)
            datas.forEach {
                LogUtil.d(TAG,"queryByAge = ${it}")
            }
        }
        getViewBinding().btnQueryAll.setOnClickListener {
            val datas = userDao.queryAll()
            datas.forEach {
                LogUtil.d(TAG,"queryAll = ${it}")
            }

            val tokenDatas = tokenDao.quaryAll()
            tokenDatas.forEach {
                LogUtil.d(TAG,"queryAll : token = ${it}")
            }

        }
        getViewBinding().btnDeleteUid.setOnClickListener {
            userDao.deleteByUid(116)
            val datas = userDao.queryAll()
            datas.forEach {
                LogUtil.d(TAG,"deleteByUid = ${it}")
            }
        }
        getViewBinding().btnDeleteUser.setOnClickListener {
            userDao.deleteByUser(User(124, "aaa", 34,"dd","测试"))
            val datas = userDao.queryAll()
            datas.forEach {
                LogUtil.d(TAG,"deleteByUser = ${it}")
            }
        }
        getViewBinding().btnDeleteAll.setOnClickListener {
            userDao.deleteAll()
            val datas = userDao.queryAll()
            LogUtil.d(TAG,"deleteAll = ${datas.size}")
        }

        getViewBinding().btnInsertToken.setOnClickListener {
            val token = TokenBean()
            token.id = 3
            token.productId = "35"
            token.token = "abc4"
            tokenDao.add(token)

            val tokenDatas = tokenDao.quaryAll()
            tokenDatas.forEach {
                LogUtil.d(TAG,"queryAll : token = ${it}")
            }
        }

        getViewBinding().btnUpdateToken.setOnClickListener {
            val token = TokenBean()
            token.id = 4
            token.productId = "30"
            token.token = "abc55"
            tokenDao.update(token)

            val tokenDatas = tokenDao.quaryAll()
            tokenDatas.forEach {
                LogUtil.d(TAG,"queryAll : token = ${it}")
            }
        }
    }

    override fun buildViewBinding(): ActivityDataBinding {
        return ActivityDataBinding.inflate(layoutInflater)
    }


}