package com.irfan.quipper_test.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfan.quipper_test.data.model.Course
import com.irfan.quipper_test.databinding.ItemCourseBinding

class CourseAdapter(private val onClick: (Course) -> Unit) :
    ListAdapter<Course, CourseAdapter.ViewHolder>(CourseDiffCallback) {

    class ViewHolder(private val itemBinding: ItemCourseBinding, private val onClick: (Course) -> Unit) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private var currentCourse: Course? = null

        init {
            itemView.setOnClickListener {
                currentCourse?.let {
                    onClick(it)
                }
            }
        }

        fun bind(course: Course) {
            currentCourse = course
            itemBinding.textTitle.text = course.title
            itemBinding.textPresenterName.text = course.presenterName
            itemBinding.textDescription.text = course.description

            Glide
                .with(itemBinding.root.context)
                .load(course.thumbnailUrl)
                .centerCrop()
                .into(itemBinding.imageThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }
}

object CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}