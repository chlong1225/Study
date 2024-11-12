package com.demo.study

import android.content.pm.PackageManager
import android.os.Bundle
import com.chl.common.utils.LogUtil
import android.graphics.Color
import com.chl.common.widget.chart.PieView
import com.demo.study.databinding.ActivityMainBinding
import com.demo.study.dialog.TestDialog
import com.demo.study.dialog.TestSheetDialog
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

        getViewBinding().btnBig.setOnClickListener {
            BigImageActivity.openSelf(this)
        }

        getViewBinding().btnFloat.setOnClickListener {
            FloatActivity.openSelf(this)
        }

        getViewBinding().btnDialog.setOnClickListener {
            val dialog = TestSheetDialog()
            dialog.show(supportFragmentManager, TestSheetDialog.TEST_SHEET_DIALOG_TAG)
        }

        val dates = mutableListOf<PieView.PieBean>()
        dates.add(PieView.PieBean(1.0, Color.RED))
        dates.add(PieView.PieBean(2.0, Color.GREEN))
        dates.add(PieView.PieBean(3.0, Color.BLUE))
        dates.add(PieView.PieBean(4.0, Color.YELLOW))
        getViewBinding().pvView.setDates(dates)

        WindowManager.window = window

        parseManifest()
    }

    /**
     * 解析AndroidManifest文件中的内容
     */
    private fun parseManifest() {
        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            val versionCode = packageInfo.versionCode
            val versionName = packageInfo.versionName
            //获取权限信息
            val permissions = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
            val activities = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).activities
            val services = packageManager.getPackageInfo(packageName,PackageManager.GET_SERVICES).services
            val provides = packageManager.getPackageInfo(packageName,PackageManager.GET_PROVIDERS).providers
            val receivers = packageManager.getPackageInfo(packageName,PackageManager.GET_RECEIVERS).receivers
            val info = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
            val value = info.metaData.getInt("InstallChannel")
            LogUtil.e("AAAA", "pareValue = $value")
        } catch (e: Exception) {
            LogUtil.e("AAAA", "paresError = ${e.message}")
        }
    }

    override fun buildViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}