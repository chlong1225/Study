package com.demo.study.wallet.tron

import java.math.BigInteger

/**
 * create on 2024/12/9
 * @author chenglong
 * description :
 */
data class TransactionInfo(
    val raw_data: RawData,
    val raw_data_hex: String,
    val txID: String,
    val visible: Boolean,
    val signature: MutableList<String> = mutableListOf()
)

data class RawData(
    val contract: List<Contract>,
    var expiration: Long,
    val ref_block_bytes: String,
    val ref_block_hash: String,
    var timestamp: Long
)

data class Contract(
    val parameter: Parameter,
    val type: String
)

data class Parameter(
    val type_url: String,
    val value: Value
)

data class Value(
    val amount: BigInteger,
    val owner_address: String,
    val to_address: String
)