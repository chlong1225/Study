package com.chl.common.net

/**
 * create on 2022/1/25
 * @author chenglong
 * description :
 */
data class ReturnModel(
    val code: Int,
    val subCode: String,
    val bodyMessage: String,
    val message: String
)

