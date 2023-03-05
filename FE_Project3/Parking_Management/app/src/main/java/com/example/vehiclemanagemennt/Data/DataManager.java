package com.example.vehiclemanagemennt.Data;

import com.example.vehiclemanagemennt.Data.Prefs.SharedPrefsHelper;

public class DataManager {
    SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void clear() {
        mSharedPrefsHelper.clear();
    }

    public void saveEmailId(String email) {
        mSharedPrefsHelper.putEmail(email);
    }

    public String getEmailId() {
        return mSharedPrefsHelper.getEmail();
    }


    public void setAreaName(String name) {mSharedPrefsHelper.saveAreaName(name);}

    public String getAreaName() {
        return mSharedPrefsHelper.getAreaName();
    }

    public void setLoggedIn() {
        mSharedPrefsHelper.setLoggedInMode(true);
    }

    public Boolean getLoggedInMode() {
        return mSharedPrefsHelper.getLoggedInMode();
    }

    public void setLoginAsManager(boolean isManager) {
        mSharedPrefsHelper.setLoginAsManager(isManager);
    }

    public Boolean isLoginAsManager() {
        return mSharedPrefsHelper.isLoginAsManager();
    }
}
