package com.demo.study.wallet.tron

/**
 * create on 2024/12/9
 * @author chenglong
 * description :
 */
data class AccountBean(
    val account_resource: AccountResource,
    val active_permission: List<ActivePermission>,
    val address: String,
    val asset_optimized: Boolean,
    val balance: Int,
    val create_time: Long,
    val frozenV2: List<FrozenV2>,
    val net_window_optimized: Boolean,
    val net_window_size: Int,
    val owner_permission: OwnerPermission
)

data class AccountResource(
    val energy_window_optimized: Boolean,
    val energy_window_size: Int
)

data class ActivePermission(
    val id: Int,
    val keys: List<Key>,
    val operations: String,
    val permission_name: String,
    val threshold: Int,
    val type: String
)

data class FrozenV2(
    val type: String
)

data class OwnerPermission(
    val keys: List<Key>,
    val permission_name: String,
    val threshold: Int
)

data class Key(
    val address: String,
    val weight: Int
)