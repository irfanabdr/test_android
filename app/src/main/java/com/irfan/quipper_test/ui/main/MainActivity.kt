package com.irfan.quipper_test.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.quipper_test.data.model.AppState
import com.irfan.quipper_test.data.model.Course
import com.irfan.quipper_test.databinding.ActivityMainBinding
import com.irfan.quipper_test.ui.details.CourseDetailActivity
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
        viewModel.state.observe(this) {
            when (it) {
                is AppState.Loading -> {
                    binding.textError.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is AppState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    courseAdapter.submitList(it.data)
                }
                is AppState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textError.text = it.message
                    binding.textError.visibility = View.VISIBLE
                }
            }
        }

        viewModel.fetchCourses()
    }

    private fun adapterOnClick(course: Course) {
        val intent = Intent(this, CourseDetailActivity()::class.java)
        intent.putExtra(COURSE_TITLE, course.title)
        startActivity(intent)
    }
}