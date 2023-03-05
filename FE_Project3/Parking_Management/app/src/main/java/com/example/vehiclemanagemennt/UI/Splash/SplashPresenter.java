package com.example.vehiclemanagemennt.UI.Splash;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.UI.Base.BasePresenter;

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V>{

    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void decideNextActivity() {
        if (getDataManager().getLoggedInMode()) {
            getMvpView().openMainActivity();
        } else {
            getMvpView().openLoginActivity();
        }
    }
}
