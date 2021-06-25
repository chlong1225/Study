package com.demo.study.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * create by chenglong on 6/25/21
 * description :
 */
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{

        private var instance: AppDatabase? = null

        fun getInstance(context: Context):AppDatabase{
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "test.db" //数据库名称
                ).allowMainThreadQueries().build()
            }
            return instance as AppDatabase
        }


    }

}