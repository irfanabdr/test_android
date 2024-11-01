package com.irfan.quipper_test.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.irfan.quipper_test.data.model.Course

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses")
    fun getAllCourses(): LiveData<List<Course>>

    @Query("SELECT * FROM courses WHERE title = :courseTitle")
    fun getCourseByTitle(courseTitle: String): LiveData<Course>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(items: List<Course>)
}