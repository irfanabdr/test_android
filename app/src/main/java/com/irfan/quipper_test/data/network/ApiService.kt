package com.irfan.quipper_test.data.network

import com.irfan.quipper_test.data.model.Course
import retrofit2.http.GET

interface ApiService {
    @GET("native-technical-exam/playlist.json")
    suspend fun getCourses(): List<Course>
}
