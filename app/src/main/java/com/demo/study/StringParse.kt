package com.demo.study

import android.content.Context
import com.chl.common.utils.LogUtil
import org.xmlpull.v1.XmlPullParser
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * create on 2022/1/6
 * @author chenglong
 * description : 解析字符串
 *
 * https://www.cnblogs.com/dasusu/p/5450919.html
 *
 * BlinkLayout的使用
 * https://blog.csdn.net/qq_22644219/article/details/69367150
 */
class StringParse {

    companion object {

        /**
         * Android原生解析xml文件
         */
        @JvmStatic
        fun parseXml(context: Context, resId: Int) {
            val startTime = System.currentTimeMillis()
            val xmlParse = context.resources.getXml(resId)
            var eventType = xmlParse.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_DOCUMENT -> LogUtil.e("AAAA", "xml开始解析")
                    XmlPullParser.START_TAG -> {
                        LogUtil.e("AAAA", "当前标签 ${xmlParse.name}")
                        val count = xmlParse.attributeCount
                        for (i in 0 until count) {
                            LogUtil.e("AAAA", "属性类型 ${xmlParse.getAttributeName(i)}")
                            LogUtil.e("AAAA", "属性值 ${xmlParse.getAttributeValue(i)}")
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        LogUtil.e("AAAA", "当前标签解析完成")
                        LogUtil.e("AAAA", "================")
                    }
                    XmlPullParser.TEXT ->{
                        LogUtil.e("AAAA", "解析的文本 = ${xmlParse.text}")
                    }
                }
                //获取下一个需要解析的标签
                eventType = xmlParse.next()
                Thread().join()
                ReentrantLock().newCondition()
            }
            LogUtil.e("AAAA", "xml结束解析 time = ${System.currentTimeMillis() - startTime}")
        }

        /**
         * Android原生解析json数据，json文件放在assets目录下
         */
        @JvmStatic
        fun parseJson(str: String) {


        }
    }
}