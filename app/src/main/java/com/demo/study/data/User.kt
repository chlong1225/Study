package com.demo.study.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * create by chenglong on 6/25/21
 * description :
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey val uid: Int,
    val name: String,
    @ColumnInfo val age: Int)
