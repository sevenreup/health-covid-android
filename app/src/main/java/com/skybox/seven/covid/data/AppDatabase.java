package com.skybox.seven.covid.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {SelfTestResult.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private final static String DB_NAME = "MalawiHealth";

    public abstract SelfTestResultDAO selfTestResultDAO();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                        .addCallback(roomCallback)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }

    private static Callback roomCallback = new Callback() {
        // Seed using a worker
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
