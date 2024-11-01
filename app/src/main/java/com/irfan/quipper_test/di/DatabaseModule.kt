package com.irfan.quipper_test.di

import android.content.Context
import androidx.room.Room
import com.irfan.quipper_test.data.database.AppDatabase
import com.irfan.quipper_test.data.database.CourseDao
import com.irfan.quipper_test.data.network.ApiService
import com.irfan.quipper_test.data.repository.CourseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "test_database").build()
    }

    @Provides
    fun provideCourseDao(database: AppDatabase): CourseDao = database.courseDao()

    @Provides
    fun provideRepository(apiService: ApiService, itemDao: CourseDao): CourseRepository {
        return CourseRepository(apiService, itemDao)
    }
}
