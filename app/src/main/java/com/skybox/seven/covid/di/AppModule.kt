package com.skybox.seven.covid.di

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.GsonBuilder
import com.skybox.seven.covid.data.AppDatabase
import com.skybox.seven.covid.data.daos.QnADAO
import com.skybox.seven.covid.data.daos.SelfTestAnswerDAO
import com.skybox.seven.covid.data.daos.SelfTestCompleteDAO
import com.skybox.seven.covid.data.daos.SelfTestQuestionDAO
import com.skybox.seven.covid.data.repositories.AdviceRepository
import com.skybox.seven.covid.data.repositories.InfoGraphicRepository
import com.skybox.seven.covid.data.repositories.LanguageRepository
import com.skybox.seven.covid.data.repositories.MythRepository
import com.skybox.seven.covid.network.HealthService
import com.skybox.seven.covid.network.RetrofitFactory
import com.skybox.seven.covid.network.RetrofitFactory.provideOfflineCacheInterceptor
import com.skybox.seven.covid.network.RetrofitFactory.provideOnlineInterceptor
import com.skybox.seven.covid.network.StatsService
import com.skybox.seven.covid.repository.SharedPreferenceRepository
import com.skybox.seven.covid.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
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
    fun providesStatsApi(@ApplicationContext context: Context): StatsService {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .client(
                        OkHttpClient()
                                .newBuilder()
                                .cache(Cache(File(context.cacheDir, "offlineCache"), 10 * 1024 * 1024))
                                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                                .addNetworkInterceptor(provideOnlineInterceptor())
                                .addNetworkInterceptor(provideOfflineCacheInterceptor())
                                .build()
                ).baseUrl(Constants.STATS_BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(StatsService::class.java)
    }
    // Databases
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getDatabase(context)

    @Singleton @Provides fun providesAdviceRepo(db: AppDatabase): AdviceRepository = AdviceRepository(db.adviceDAO())
    @Singleton @Provides fun providesInfoRepo(db: AppDatabase): InfoGraphicRepository = InfoGraphicRepository(db.infoGraphicDAO())
    @Singleton @Provides fun providesLanguageRepo(db: AppDatabase): LanguageRepository = LanguageRepository(db.languageDAO())
    @Singleton @Provides fun providesMythsRepo(db: AppDatabase): MythRepository = MythRepository(db.mythsDAO())
    @Singleton @Provides fun providesSelfTestQuestions(db: AppDatabase): SelfTestQuestionDAO = db.selfTestQuestionDAO()
    @Singleton @Provides fun providesSelfTestAnswers(db: AppDatabase): SelfTestAnswerDAO = db.selfTestAnswerDAO()
    @Singleton @Provides fun providesSelfTestComplete(db: AppDatabase): SelfTestCompleteDAO = db.selfTestCompleteDAO()
    @Singleton @Provides fun providesQnA(db: AppDatabase): QnADAO = db.qnADAO()

    // preferences
    @Singleton @Provides fun providesSharedPrefRepo(@ApplicationContext context: Context): SharedPreferenceRepository =
            SharedPreferenceRepository(PreferenceManager.getDefaultSharedPreferences(context), context)

    @Provides
    fun provideDisposable(): CompositeDisposable = CompositeDisposable()
}