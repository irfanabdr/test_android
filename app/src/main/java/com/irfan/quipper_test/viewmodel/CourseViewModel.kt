package com.irfan.quipper_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfan.quipper_test.data.model.AppState
import com.irfan.quipper_test.data.model.Course
import com.irfan.quipper_test.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(private val repository: CourseRepository) : ViewModel() {

    private val _state = MutableLiveData<AppState<List<Course>>>()
    val state: LiveData<AppState<List<Course>>> get() = _state

    fun fetchCourses() {
        viewModelScope.launch {
            _state.value = AppState.Loading

            val cachedData = repository.getCachedData()
            if (cachedData.isNotEmpty()) {
                _state.value = AppState.Success(cachedData)
            }

            try {
                val courses = repository.fetchCourses()
                repository.saveToCache(courses)
                _state.value = AppState.Success(courses)
            } catch (e: Exception) {
                if (cachedData.isEmpty()) {
                    _state.value = AppState.Error("No course available.")
                }
            }
        }
    }
}
