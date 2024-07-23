package com.chl.common.net

/**
 * create on 2024/7/23
 * @author chenglong
 * description :
 */
interface DownloadFileListener {

    fun onDownloadSuccess(path: String)

    fun onDownloadProgress(progress: Double)

    fun onDownloadFail(message: String?)
}