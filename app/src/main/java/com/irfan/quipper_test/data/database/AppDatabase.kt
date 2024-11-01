package com.irfan.quipper_test.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.irfan.quipper_test.data.model.Course

@Database(entities = [Course::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}