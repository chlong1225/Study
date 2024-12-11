package com.demo.study.wallet.tron

import java.math.BigInteger

/**
 * create on 2024/12/10
 * @author chenglong
 * description :
 */
data class PayInfo (
    val owner_address:String,
    val to_address:String,
    val amount: BigInteger,
    val visible:Boolean = true
)