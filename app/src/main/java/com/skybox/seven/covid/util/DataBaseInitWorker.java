package com.skybox.seven.covid.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.skybox.seven.covid.data.AppDatabase;

public class DataBaseInitWorker extends Worker {
    private Context context;
    public DataBaseInitWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        AppDatabase.getDatabase(context).languageDAO().insertAll(RandomSeeders.setUpLanguages());
        AppDatabase.getDatabase(context).adviceDAO().insertAll(RandomSeeders.setUpAdvice());
        AppDatabase.getDatabase(context).mythsDAO().insertAll(RandomSeeders.setUpMyths());
        AppDatabase.getDatabase(context).infoGraphicDAO().insertAll(RandomSeeders.setUpInfoGraphic());
        AppDatabase.getDatabase(context).infoGraphicDAO().insertAll(RandomSeeders.setUpMythGraphicInfo());
        return Result.success();
    }
}