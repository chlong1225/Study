package com.demo.study

import android.os.Bundle
import com.chl.common.utils.LogUtil
import android.graphics.Color
import com.chl.common.widget.chart.PieView
import com.demo.study.databinding.ActivityMainBinding
import com.demo.study.list.RvListActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewBinding().btnKotlin.setOnClickListener {
            LogUtil.e("AAAA", "onClick : kotlin")
            KotlinActivity.openSelf(this@MainActivity)
        }
        getViewBinding().btnSet.setOnClickListener {
            LogUtil.e("AAAA","onClick : set : $it")
            SetActivity.openSelf(this@MainActivity)
        }
        getViewBinding().btnData.setOnClickListener { DataActivity.openSelf(this@MainActivity) }

        getViewBinding().btnParseXml.setOnClickListener {
            StringParse.parseXml(this, R.layout.xml_test)
//            StringParse.parseXml(this, R.layout.xml_test2)
        }
        getViewBinding().btnStatusBar.setOnClickListener {
            StatusBarActivity.openSelf(this@MainActivity)
        }

        getViewBinding().btnLifecycle.setOnClickListener {
            LifecycleActivity.openSelf(this)
        }

        getViewBinding().btnRvList.setOnClickListener {
            RvListActivity.openSelf(this)
        }

        getViewBinding().btnPdf.setOnClickListener {
            PdfActivity.openSelf(this)
        }

        getViewBinding().btnFloat.setOnClickListener {
            FloatActivity.openSelf(this)
        }

        val dates = mutableListOf<PieView.PieBean>()
        dates.add(PieView.PieBean(1.0, Color.RED))
        dates.add(PieView.PieBean(2.0, Color.GREEN))
        dates.add(PieView.PieBean(3.0, Color.BLUE))
        dates.add(PieView.PieBean(4.0, Color.YELLOW))
        getViewBinding().pvView.setDates(dates)

        WindowManager.window = window
    }

    override fun buildViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}