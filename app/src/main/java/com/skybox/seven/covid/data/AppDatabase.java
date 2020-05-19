package com.skybox.seven.covid.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.skybox.seven.covid.data.daos.AdviceDAO;
import com.skybox.seven.covid.data.daos.LanguageDAO;
import com.skybox.seven.covid.data.daos.MythsDAO;
import com.skybox.seven.covid.data.daos.SelfTestResultDAO;
import com.skybox.seven.covid.data.entities.Language;
import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.data.entities.SelfTestResult;
import com.skybox.seven.covid.repository.RandomSeeders;
import com.skybox.seven.covid.util.InjectorUtil;

import java.util.concurrent.Executors;

@Database(entities = {SelfTestResult.class, Myth.class, Language.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private final static String DB_NAME = "MalawiHealth";

    public abstract SelfTestResultDAO selfTestResultDAO();
    public abstract MythsDAO mythsDAO();
    public abstract LanguageDAO languageDAO();
    public abstract AdviceDAO adviceDAO();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                        .addCallback(new Callback() {
                            // Seed using a worker
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.e("stuff", "adding stuff");
                                        InjectorUtil.getLanguageRepository(context).addAllLanguages(RandomSeeders.setUpLanguages());
                                    }
                                });
                            }
                        })
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }
}
