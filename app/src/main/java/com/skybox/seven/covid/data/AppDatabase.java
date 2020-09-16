package com.skybox.seven.covid.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.skybox.seven.covid.BuildConfig;
import com.skybox.seven.covid.data.daos.AdviceDAO;
import com.skybox.seven.covid.data.daos.InfoGraphicDAO;
import com.skybox.seven.covid.data.daos.LanguageDAO;
import com.skybox.seven.covid.data.daos.MythsDAO;
import com.skybox.seven.covid.data.daos.QnADAO;
import com.skybox.seven.covid.data.daos.SelfTestAnswerDAO;
import com.skybox.seven.covid.data.daos.SelfTestCompleteDAO;
import com.skybox.seven.covid.data.daos.SelfTestQuestionDAO;
import com.skybox.seven.covid.data.entities.Advice;
import com.skybox.seven.covid.data.entities.InfoGraphic;
import com.skybox.seven.covid.data.entities.Language;
import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.data.entities.Qna;
import com.skybox.seven.covid.data.entities.SelfTestAnswer;
import com.skybox.seven.covid.data.entities.SelfTestComplete;
import com.skybox.seven.covid.data.entities.SelfTestQuestion;
import com.skybox.seven.covid.helpers.RoomTypeConverters;
import com.skybox.seven.covid.util.DataBaseInitWorker;

import java.util.concurrent.Executors;

@Database(entities = {SelfTestAnswer.class, SelfTestQuestion.class, SelfTestComplete.class, Myth.class, Advice.class, InfoGraphic.class, Language.class, Qna.class}, version = BuildConfig.HEALTH_DB_VERSION)
@TypeConverters({RoomTypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = "AppDatabase";
    private static AppDatabase INSTANCE;
    private final static String DB_NAME = "MalawiHealth";

    public abstract SelfTestAnswerDAO selfTestAnswerDAO();
    public abstract SelfTestQuestionDAO selfTestQuestionDAO();
    public abstract SelfTestCompleteDAO selfTestCompleteDAO();
    public abstract MythsDAO mythsDAO();
    public abstract LanguageDAO languageDAO();
    public abstract AdviceDAO adviceDAO();
    public abstract InfoGraphicDAO infoGraphicDAO();
    public abstract QnADAO qnADAO();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                        .allowMainThreadQueries()
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadExecutor().execute(() -> {
                                    OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(DataBaseInitWorker.class).build();
                                    WorkManager.getInstance(context).enqueue(workRequest);
                                });
                            }
                        })
                        .build();
            }
        }
        return INSTANCE;
    }

    public static AppDatabase initStuff (final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                        .allowMainThreadQueries()
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Log.e(TAG, "onCreating");
                                Executors.newSingleThreadExecutor().execute(() -> {
                                    OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(DataBaseInitWorker.class).build();
                                    WorkManager.getInstance(context).enqueue(workRequest);
                                });
                            }
                        })
                        .build();
            }
        }
        return INSTANCE;
    }
}
