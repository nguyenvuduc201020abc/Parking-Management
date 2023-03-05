package com.example.vehiclemanagemennt.Data.Prefs;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

public class SharedPrefsHelper {
    public static final String MY_PREFS = "MY_PREFS";

    public static final String EMAIL = "EMAIL";

    public static final String NAME_AREA = "NAME_AREA";

    SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }

    public void putEmail(String email) {
        mSharedPreferences.edit().putString(EMAIL, email).apply();
    }

    public void saveAreaName(String name) {
        mSharedPreferences.edit().putString(NAME_AREA, name).apply();
    }
    public String getAreaName() {
        return mSharedPreferences.getString(NAME_AREA, null);
    }

    public String getEmail() {
        return mSharedPreferences.getString(EMAIL, null);
    }

    public boolean getLoggedInMode() {
        return mSharedPreferences.getBoolean("IS_LOGGED_IN", false);
    }

    public void setLoginAsManager(boolean asManager) {
        mSharedPreferences.edit().putBoolean("IS_MANAGER", asManager).apply();
    }

    public boolean isLoginAsManager() {
        return mSharedPreferences.getBoolean("IS_MANAGER", false);
    }


    public void setLoggedInMode(boolean loggedIn) {
        mSharedPreferences.edit().putBoolean("IS_LOGGED_IN", loggedIn).apply();
    }
}
