package com.irfan.quipper_test.data.repository

import androidx.lifecycle.LiveData
import com.irfan.quipper_test.data.database.CourseDao
import com.irfan.quipper_test.data.model.Course
import com.irfan.quipper_test.data.network.ApiService
import javax.inject.Inject

class CourseRepository @Inject constructor(private val apiService: ApiService, private val courseDao: CourseDao) {

    suspend fun fetchCourses(): List<Course> {
        return apiService.getCourses()
    }

    suspend fun getCachedData(): List<Course> {
        return courseDao.getAllCourses()
    }

    suspend fun saveToCache(courses: List<Course>) {
        courseDao.insertCourses(courses)
    }

    fun getCourseByTitle(title: String): LiveData<Course> {
        return courseDao.getCourseByTitle(title)
    }
}
