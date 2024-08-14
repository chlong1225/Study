package com.demo.study

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.BitmapRegionDecoder
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.chl.common.utils.LogUtil
import com.chl.common.utils.ScreenUtil
import com.demo.study.databinding.ActivityBigImageBinding

/**
 * create on 2024/8/14
 * @author chenglong
 * description :
 */
class BigImageActivity : BaseActivity<ActivityBigImageBinding>() {

    companion object{

        fun openSelf(ctx: Context) {
            val intent = Intent(ctx, BigImageActivity::class.java)
            ctx.startActivity(intent)
        }
    }

    override fun buildViewBinding(): ActivityBigImageBinding {
        return ActivityBigImageBinding.inflate(layoutInflater)
    }

    private var imageWidth = 0
    private var imageHeight = 0
    private val bounds = Rect()
    private var startX = 0f
    private var startY = 0f

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.big, options)
        //获取图片的宽高
        imageWidth = options.outWidth
        imageHeight = options.outHeight
        LogUtil.e("AAAA", "imageWidth = $imageWidth ;; imageHeight = $imageHeight")

        try {
            val inputStream = resources.openRawResource(R.drawable.big)
            val bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false)
            bitmapRegionDecoder?.let {
                val showWidth = ScreenUtil.getScreenWidth() - ScreenUtil.dp2px(10f * 2)
                val showHeight = ScreenUtil.dp2px(300f)
                bounds.left = imageWidth / 2 - showWidth / 2
                bounds.right = imageWidth / 2 + showWidth / 2
                bounds.top = imageHeight / 2 - showHeight / 2
                bounds.bottom = imageHeight / 2 + showHeight / 2
                val bitmap = it.decodeRegion(bounds, null)
                getViewBinding().ivShow.setImageBitmap(bitmap)
                getViewBinding().ivShow.setOnTouchListener { v, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        startX = event.rawX
                        startY = event.rawY
                    } else if (event.action == MotionEvent.ACTION_MOVE) {
                        val dx = (event.rawX - startX).toInt()
                        val dy = (event.rawY - startY).toInt()
                        bounds.left -= dx
                        bounds.right -= dx
                        bounds.top -= dy
                        bounds.bottom -= dy
                        if (bounds.left < 0) {
                            val tem = bounds.left
                            bounds.left = 0
                            bounds.right -= tem
                        } else if (bounds.right > imageWidth) {
                            val tem = bounds.right
                            bounds.right = imageWidth
                            bounds.left -= (tem - bounds.right)
                        }
                        if (bounds.top < 0) {
                            val tem = bounds.top
                            bounds.top = 0
                            bounds.bottom -= tem
                        } else if (bounds.bottom > imageHeight) {
                            val tem = bounds.bottom
                            bounds.bottom = imageHeight
                            bounds.top -= (tem - bounds.bottom)
                        }
                        val bitmap = it.decodeRegion(bounds, null)
                        getViewBinding().ivShow.setImageBitmap(bitmap)
                        startX = event.rawX
                        startY = event.rawY
                    }
                    true
                }
            }
        } catch (e: Exception) {
            LogUtil.e("AAAA", "error = ${e.message}")
        }
    }
}