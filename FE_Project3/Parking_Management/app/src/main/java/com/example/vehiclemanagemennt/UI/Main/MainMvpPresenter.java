package com.example.vehiclemanagemennt.UI.Main;

import com.example.vehiclemanagemennt.UI.Base.MvpPresenter;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    String getAccount();
    void setUserLoggedOut();
}
