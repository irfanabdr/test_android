package com.irfan.quipper_test.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.quipper_test.data.model.Course
import com.irfan.quipper_test.databinding.ActivityMainBinding
import com.irfan.quipper_test.viewmodel.CourseViewModel
import dagger.hilt.android.AndroidEntryPoint

const val COURSE_TITLE = "course_title"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CourseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val courseAdapter = CourseAdapter { adapterOnClick(it) }
        binding.recyclerViewCourse.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCourse.adapter = courseAdapter
        viewModel.courses.observe(this) {
            courseAdapter.submitList(it)
        }

        viewModel.refreshCourses()
    }

    private fun adapterOnClick(course: Course) {
        Toast.makeText(applicationContext, course.title, Toast.LENGTH_SHORT).show()
//        val intent = Intent(this, CourseDetailActivity()::class.java)
//        intent.putExtra(COURSE_TITLE, course.title)
//        startActivity(intent)
    }
}