package com.irfan.quipper_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.irfan.quipper_test.data.model.Course
import com.irfan.quipper_test.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CourseDetailViewModel @Inject constructor(private val repository: CourseRepository) : ViewModel() {

    fun getCourseByTitle(title: String): LiveData<Course> {
        return repository.getCourseByTitle(title)
    }
}