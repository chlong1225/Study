package com.demo.study.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * create by chenglong on 6/25/21
 * description :
 */
@Entity(tableName = "user")
data class User(
    //一个字段可以使用多个注解
    @PrimaryKey
    @ColumnInfo(name = "userId")
    var uid: Int,
    //使用变量名为字段时，不需要添加ColumnInfo注解
    var name: String,
    @ColumnInfo var age: Int,
    var description: String,
    @Ignore var remarks: String
){
    /**
     * 添加忽略字段Ignore注解时会报错：Entities and POJOs must have a usable public constructor.需要添加空参构造方法
     */
    constructor() : this(0, "", 0, "","")
}
