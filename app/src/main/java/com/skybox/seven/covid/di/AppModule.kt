package com.skybox.seven.covid.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.AppDatabase
import com.skybox.seven.covid.data.daos.SelfTestAnswerDAO
import com.skybox.seven.covid.data.daos.SelfTestQuestionDAO
import com.skybox.seven.covid.data.repositories.*
import com.skybox.seven.covid.network.*
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import com.skybox.seven.covid.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun providesStatsApi(@ApplicationContext context: Context): StatsService {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .client(NewsFactory.buildOkHttpClient(context))
                .baseUrl(Constants.STATS_BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(StatsService::class.java)
    }
    // Databases
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase =
            AppDatabase.getDatabase(context)
    @Singleton @Provides fun providesAdviceRepo(db: AppDatabase): AdviceRepository = AdviceRepository(db.adviceDAO())
    @Singleton @Provides fun providesInfoRepo(db: AppDatabase): InfoGraphicRepository = InfoGraphicRepository(db.infoGraphicDAO())
    @Singleton @Provides fun providesLanguageRepo(db: AppDatabase): LanguageRepository = LanguageRepository(db.languageDAO())
    @Singleton @Provides fun providesMythsRepo(db: AppDatabase): MythRepository = MythRepository(db.mythsDAO())

    @Singleton @Provides fun providesSelfTestQuestions(db: AppDatabase): SelfTestQuestionDAO = db.selfTestQuestionDAO()
    @Singleton @Provides fun providesSelfTestAnswers(db: AppDatabase): SelfTestAnswerDAO = db.selfTestAnswerDAO()

    // preferences
    @Singleton @Provides fun providesSharedPrefRepo(@ApplicationContext context: Context): SharedPreferenceRepository =
            SharedPreferenceRepository(context.getSharedPreferences(context.getString(R.string.shared_preference_key), Context.MODE_PRIVATE))

    @Provides
    fun provideDisposable(): CompositeDisposable = CompositeDisposable()
}