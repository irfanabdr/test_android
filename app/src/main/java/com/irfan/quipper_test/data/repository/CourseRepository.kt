package com.irfan.quipper_test.data.repository

import androidx.lifecycle.LiveData
import com.irfan.quipper_test.data.database.CourseDao
import com.irfan.quipper_test.data.model.Course
import com.irfan.quipper_test.data.network.ApiService
import javax.inject.Inject

class CourseRepository @Inject constructor(private val apiService: ApiService, private val courseDao: CourseDao) {

    val courses: LiveData<List<Course>> = courseDao.getAllCourses()

    suspend fun refreshCourses() {
        try {
            val itemsFromNetwork = apiService.getCourses()
            courseDao.insertCourses(itemsFromNetwork)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getCourseByTitle(title: String): LiveData<Course> {
        return courseDao.getCourseByTitle(title)
    }
}
