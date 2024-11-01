package com.irfan.quipper_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfan.quipper_test.data.model.Course
import com.irfan.quipper_test.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(private val repository: CourseRepository) : ViewModel() {

    val courses: LiveData<List<Course>> = repository.courses

    fun refreshCourses() {
        viewModelScope.launch {
            repository.refreshCourses()
        }
    }
}
