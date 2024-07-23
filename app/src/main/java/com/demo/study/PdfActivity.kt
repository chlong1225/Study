package com.demo.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.chl.common.net.DownloadFileListener
import com.chl.common.net.OkHttpManager
import com.demo.study.databinding.ActivityPdfBinding
import java.io.File

/**
 * create on 2024/7/22
 * @author chenglong
 * description :
 * js方案参考：
 * https://github.com/mozilla/pdf.js/blob/master/src/pdf.js
 * https://blog.csdn.net/taoyuxin1314/article/details/97004996
 */
class PdfActivity : BaseActivity<ActivityPdfBinding>() {

    companion object {

        private const val url = "https://img.fastbull.com/test/202406/a8ea13c73879b3d370f20c21d6a1569b1718393285708.pdf"

        fun openSelf(context: Context) {
            context.startActivity(Intent(context, PdfActivity::class.java))
        }
    }

    override fun buildViewBinding(): ActivityPdfBinding {
        return ActivityPdfBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        OkHttpManager.instance.init(true)
        val files = File(filesDir, "test.pdf")
        OkHttpManager.instance.downloadFile(url, files.absolutePath, object : DownloadFileListener {
            override fun onDownloadSuccess(path: String) {
                Toast.makeText(this@PdfActivity, "加载完成 = ${Thread.currentThread().name}", Toast.LENGTH_SHORT).show()
                showPdf(path)
            }

            override fun onDownloadProgress(progress: Double) {
            }

            override fun onDownloadFail(message: String?) {
            }
        })
    }

    private fun showPdf(path: String) {
        getViewBinding().pdfView
            .fromFile(File(path))
            .pages(0, 1, 2, 3, 4)
            .load()
    }
}