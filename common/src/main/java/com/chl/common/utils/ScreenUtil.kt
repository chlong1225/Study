package com.chl.common.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.annotation.Px
import androidx.core.view.ContentInfoCompat.Flags

/**
 * create on 2024/7/29
 * @author chenglong
 * description : 获取屏幕相关的尺寸
 */
object ScreenUtil {

    private const val DEFAULT_SIZE = -1
    private const val DEFAULT_SIZE_FLOAT = -1f

    /**
     * 屏幕的宽高
     */
    private var screenW = DEFAULT_SIZE
    private var screenH = DEFAULT_SIZE

    /**
     * 获取屏幕真实高度(跟虚拟按键区无关)
     */
    private var screenRealH = DEFAULT_SIZE

    /**
     * dp转px的系数
     */
    private var screenDensity = DEFAULT_SIZE_FLOAT

    /**
     * sp转px的系数
     */
    private var screenScaledDensity = DEFAULT_SIZE_FLOAT

    //状态栏的高度
    private var statusBarHeight = DEFAULT_SIZE

    //虚拟按键的高度
    private var virtualBarHeight = DEFAULT_SIZE

    //导航栏的高度
    private var navigationBarHeight = DEFAULT_SIZE

    /**
     * 初始化
     */
    @JvmStatic
    fun init(ctx: Context) {
        checkContext(ctx)
        resetScreen(ctx)
        statusBarHeight = initStatusBarHeight(ctx)
        virtualBarHeight = initVirtualBarHeight(ctx)
        navigationBarHeight = initNavigationBarHeight(ctx)
        initUnit(ctx)
    }

    /**
     * 切换虚拟按键显示隐藏时需要重新获取屏幕尺寸
     */
    @JvmStatic
    fun resetScreen(ctx: Context){
        checkContext(ctx)
        val displayMetrics = ctx.resources.displayMetrics
        screenW = displayMetrics.widthPixels
        screenH = displayMetrics.heightPixels
        screenRealH = getRealHeight(ctx)
    }

    /**
     * 部分屏幕适配方案会修改dp，sp转px的系数。此时需要重置
     */
    @JvmStatic
    fun initUnit(ctx: Context){
        checkContext(ctx)
        val displayMetrics = ctx.resources.displayMetrics
        screenDensity = displayMetrics.density
        screenScaledDensity =displayMetrics.scaledDensity
    }

    /**
     * 获取屏幕的宽度
     */
    @JvmStatic
    fun getScreenWidth(): Int {
        checkSize(screenW)
        return screenW
    }

    /**
     * 获取屏幕的高度
     */
    @JvmStatic
    fun getScreenHeight(): Int {
        checkSize(screenH)
        return screenH
    }

    /**
     * 获取屏幕真实高度(跟虚拟按键区无关)
     */
    @JvmStatic
    fun getScreenRealH(): Int {
        checkSize(screenRealH)
        return screenRealH
    }

    /**
     * 获取状态栏的高度
     */
    @JvmStatic
    fun getStatusBarHeight(): Int {
        checkSize(statusBarHeight)
        return statusBarHeight
    }

    /**
     * 获取虚拟按键的高度
     */
    @JvmStatic
    fun getVirtualBarHeight(): Int {
        checkSize(virtualBarHeight)
        return virtualBarHeight
    }

    /**
     * 获取导航栏的高度
     */
    @JvmStatic
    fun getNavigationBarHeight(): Int {
        checkSize(navigationBarHeight)
        return navigationBarHeight
    }

    /**
     * dp转px
     */
    @JvmStatic
    fun dp2px(dpValue: Float):Int{
        checkSize(screenDensity)
        return (dpValue * screenDensity + 0.5f).toInt()
    }

    /**
     * px转dp
     */
    @JvmStatic
    fun px2dp(pxValue: Int): Float {
        checkSize(screenDensity)
        return pxValue * 1.0f / screenDensity
    }

    /**
     * sp转px
     */
    @JvmStatic
    fun sp2px(spValue: Float): Int {
        checkSize(screenScaledDensity)
        return (spValue * screenScaledDensity + 0.5f).toInt()
    }

    /**
     * px转sp
     */
    @JvmStatic
    fun px2sp(pxValue:Int):Float{
        checkSize(screenScaledDensity)
        return pxValue * 1.0f / screenScaledDensity
    }

    private fun checkSize(size: Float) {
        if (size == DEFAULT_SIZE_FLOAT) {
            throw java.lang.IllegalArgumentException("请先调用init()方法进行初始化")
        }
    }

    private fun checkSize(size: Int) {
        if (size == DEFAULT_SIZE) {
            throw java.lang.IllegalArgumentException("请先调用init()方法进行初始化")
        }
    }

    private fun initStatusBarHeight(ctx: Context):Int{
        val resources = ctx.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId)
        }
        return 0
    }

    private fun initVirtualBarHeight(ctx: Context):Int{
        val windowManager = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay = windowManager.defaultDisplay
        val dm = DisplayMetrics()
        try {
            val clazz = Class.forName("android.view.Display")
            val method = clazz.getMethod("getRealMetrics", DisplayMetrics::class.java)
            method.invoke(defaultDisplay, dm)
            return dm.heightPixels - windowManager.defaultDisplay.height
        } catch (e: Exception) {
        }
        return 0
    }

    private fun initNavigationBarHeight(ctx: Context): Int {
        val resources = ctx.resources
        val rid = resources.getIdentifier("config_showNavigationBar", "bool", "android")
        if (rid != 0) {
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            return resources.getDimensionPixelSize(resourceId)
        }
        return 0
    }

    private fun getRealHeight(ctx: Context): Int {
        val windowManager = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay = windowManager.defaultDisplay ?: return 0
        val displayMetrics = DisplayMetrics()
        defaultDisplay.getRealMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    private fun checkContext(ctx: Context) {
        if (ctx is Activity || ctx is Application) {
            return
        }
        throw IllegalArgumentException("传入的context参数类型不对")
    }
}