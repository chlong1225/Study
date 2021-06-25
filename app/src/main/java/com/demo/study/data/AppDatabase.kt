package com.demo.study.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * create by chenglong on 6/25/21
 * description :
 */
@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var instance: AppDatabase? = null

        val migration_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("delete from user")
                //添加字段时必须加上默认值与 not null
                database.execSQL("alter table user add column description TEXT default '' not null")
            }

        }

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "test.db" //数据库名称
                ).addMigrations(migration_1_2).allowMainThreadQueries().build()
            }
            return instance as AppDatabase
        }
    }

}