package com.well.newscleanarch.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.well.newscleanarch.data.local.NewsDao
import com.well.newscleanarch.data.local.NewsLocalDatabase
import com.well.newscleanarch.data.local.NewsTypeConverter
import com.well.newscleanarch.data.manager.LocalUserManagerImpl
import com.well.newscleanarch.data.remote.NewsDataSource
import com.well.newscleanarch.data.repository.NewsRepositoryImpl
import com.well.newscleanarch.domain.manager.LocalUserManager
import com.well.newscleanarch.domain.repository.NewsRepository
import com.well.newscleanarch.domain.usecases.app_entry.AppEntryUseCases
import com.well.newscleanarch.domain.usecases.app_entry.ReadAppEntry
import com.well.newscleanarch.domain.usecases.app_entry.SaveAppEntry
import com.well.newscleanarch.domain.usecases.news.remote.GetNewsUseCase
import com.well.newscleanarch.domain.usecases.news.NewsUseCases
import com.well.newscleanarch.domain.usecases.news.remote.SearchNewsUseCase
import com.well.newscleanarch.domain.usecases.news.room.DeleteArticleUseCase
import com.well.newscleanarch.domain.usecases.news.room.SelectArticleUseCase
import com.well.newscleanarch.domain.usecases.news.room.SelectArticlesUseCase
import com.well.newscleanarch.domain.usecases.news.room.UpsertArticleUseCase
import com.well.newscleanarch.util.ApiUrlConstant
import com.well.newscleanarch.util.Constants
import com.well.newscleanarch.util.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) =
        AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )

    @Singleton
    @Provides
    @Named(Constants.RETROFIT_DEFAULT)
    fun provideNewsApi(
        @ApplicationContext appContext: Context,
    ): Retrofit = Network.retrofitClient(ApiUrlConstant.BASE_URL, appContext)

    @Provides
    @Singleton
    fun provideNetworkDatasource(
        @Named(Constants.RETROFIT_DEFAULT) retrofit: Retrofit
    ): NewsDataSource = retrofit.create(NewsDataSource::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsDataSource,
        newsDao: NewsDao
    ): NewsRepository =
        NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases(
            getNewsUseCase = GetNewsUseCase(newsRepository),
            searchNewsUseCase = SearchNewsUseCase(newsRepository),
            upsertArticleUseCase = UpsertArticleUseCase(newsRepository),
            deleteArticleUseCase = DeleteArticleUseCase(newsRepository),
            selectArticlesUseCase = SelectArticlesUseCase(newsRepository),
            selectArticleUseCase = SelectArticleUseCase(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsLocalDatabase(
        application: Application
    ): NewsLocalDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsLocalDatabase::class.java,
            name = Constants.NEWS_LOCAL_DB_NAME
        ).addTypeConverter(NewsTypeConverter()).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsLocalDatabase: NewsLocalDatabase
    ) = newsLocalDatabase.newsDao


}