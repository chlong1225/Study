package com.demo.study.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * create by chenglong on 6/25/21
 * description :
 */
@Dao
interface UserDao {

    @Insert
    fun add(user: User)

    //ASC ： 升序 ； DESC ： 降序
    @Query("SELECT * FROM user WHERE age = :age ORDER BY uid DESC")
    fun queryByAge(age: Int): List<User>

    //默认以主键升序进行排列
    @Query("SELECT * FROM user")
    fun queryAll(): List<User>

    @Query("delete from user where uid = :uid")
    fun deleteByUid(uid: Int)

    //删除主键相同的数据，其它属性对应的值不会进行对比
    @Delete
    fun deleteByUser(user: User)

    @Query("delete from user")
    fun deleteAll()

}