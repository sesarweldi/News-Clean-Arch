package com.well.newscleanarch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.well.newscleanarch.domain.model.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsLocalDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao
}