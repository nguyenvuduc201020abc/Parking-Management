package com.example.vehiclemanagemennt;

import android.app.Application;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.Data.Prefs.SharedPrefsHelper;

public class MvpApp extends Application {
    private DataManager dataManager;
    @Override
    public  void onCreate() {
        super.onCreate();

        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
        dataManager = new DataManager(sharedPrefsHelper);
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}