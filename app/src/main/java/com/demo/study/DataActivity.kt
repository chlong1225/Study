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
            userDao.add(User(120, "haha1", 20,101,"测试1"))
            userDao.add(User(121, "haha2", 21,102,"测试2"))
            userDao.add(User(122, "haha3", 20,103,"测试3"))
            userDao.add(User(117, "haha4", 21,104,"测试4"))
            userDao.add(User(116, "haha5", 19,105,"测试5"))
            userDao.add(User(125, "haha6", 20,106,"测试6"))
            userDao.add(User(124, "haha7", 20,107,"测试7"))

            val token1 = TokenBean()
            token1.productId = "25"
            token1.token = "abc1"
            token1.time = System.currentTimeMillis()
            tokenDao.add(token1)

            val token2 = TokenBean()
            token2.productId = "26"
            token2.token = "abc2"
            token2.time = System.currentTimeMillis() + 10
            tokenDao.add(token2)

            val token3 = TokenBean()
            token3.productId = "27"
            token3.token = "abc3"
            token3.time = System.currentTimeMillis() + 20
            tokenDao.add(token3)

            val token4 = TokenBean()
            token4.productId = "25"
            token4.token = "abc4"
            token4.time = System.currentTimeMillis() + 30
            tokenDao.add(token4)

            val token5 = TokenBean()
            token5.productId = "26"
            token5.token = "abc5"
            token5.time = System.currentTimeMillis() + 40
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
            userDao.deleteByUser(User(124, "aaa", 34,108,"测试"))
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
            token.time = System.currentTimeMillis() + 50
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