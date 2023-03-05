package com.example.vehiclemanagemennt.UI.Splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.vehiclemanagemennt.R;

import androidx.annotation.Nullable;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.MvpApp;
import com.example.vehiclemanagemennt.UI.Login.LoginActivity;
import com.example.vehiclemanagemennt.UI.Main.MainActivity;

public class SplashActivity extends Activity implements SplashMvpView{
    private SplashPresenter mSplashPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        mSplashPresenter = new SplashPresenter(dataManager);
        mSplashPresenter.onAttach(this);
        mSplashPresenter.decideNextActivity();
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }
}
