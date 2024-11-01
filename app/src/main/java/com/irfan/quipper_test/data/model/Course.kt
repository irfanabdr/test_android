package com.irfan.quipper_test.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "courses")
data class Course(
    @PrimaryKey val title: String,
    @SerializedName("presenter_name") val presenterName: String,
    val description: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("video_url") val videoUrl: String,
    @SerializedName("video_duration") val videoDuration: Int,
)
