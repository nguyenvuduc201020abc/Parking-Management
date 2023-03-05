package com.example.vehiclemanagemennt.UI.Splash;

import com.example.vehiclemanagemennt.UI.Base.MvpPresenter;

public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
    void decideNextActivity();
}
