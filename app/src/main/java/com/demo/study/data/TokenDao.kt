package com.demo.study.data

import androidx.room.*

/**
 * create by chenglong on 6/29/21
 * description :
 */
@Dao
interface TokenDao {

    /**
     * 插入相同主键时，默认：OnConflictStrategy.ABORT模式。会报错
     * OnConflictStrategy.IGNORE：忽略当前操作
     * OnConflictStrategy.REPLACE：替换当前主键对应的数据，相当于update
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(bean: TokenBean)

    //只有主键相同时才会更新当前数据，否则数据不变
    @Update
    fun update(bean: TokenBean)

    @Query("select * from token")
    fun quaryAll():List<TokenBean>

}