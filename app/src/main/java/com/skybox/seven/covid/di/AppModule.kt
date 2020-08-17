package com.skybox.seven.covid.di

import android.content.Context
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.AppDatabase
import com.skybox.seven.covid.data.daos.SelfTestResultDAO
import com.skybox.seven.covid.data.repositories.*
import com.skybox.seven.covid.network.HealthService
import com.skybox.seven.covid.network.NewsFactory
import com.skybox.seven.covid.network.NewsService
import com.skybox.seven.covid.network.RetrofitFactory
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    // Retrofit services
    @Singleton
    @Provides
    fun provideHealthApi(@ApplicationContext context: Context): HealthService =
            RetrofitFactory.getRetrofit(context).create(HealthService::class.java)
    @Singleton
    @Provides
    fun providesNewsApi(@ApplicationContext context: Context): NewsService =
            NewsFactory.getRetrofit(context).create(NewsService::class.java)

    // Databases
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase =
            AppDatabase.getDatabase(context)
    @Singleton @Provides fun providesAdviceRepo(db: AppDatabase): AdviceRepository = AdviceRepository(db.adviceDAO())
    @Singleton @Provides fun providesInfoRepo(db: AppDatabase): InfoGraphicRepository = InfoGraphicRepository(db.infoGraphicDAO())
    @Singleton @Provides fun providesLanguageRepo(db: AppDatabase): LanguageRepository = LanguageRepository(db.languageDAO())
    @Singleton @Provides fun providesMythsRepo(db: AppDatabase): MythRepository = MythRepository(db.mythsDAO())
    @Singleton @Provides fun providesSelfTestRepo(db: AppDatabase): SelfTestRepository = SelfTestRepository(db.selfTestResultDAO())

    // preferences
    @Singleton @Provides fun providesSharedPrefRepo(@ApplicationContext context: Context): SharedPreferenceRepository =
            SharedPreferenceRepository(context.getSharedPreferences(context.getString(R.string.shared_preference_key), Context.MODE_PRIVATE))
}