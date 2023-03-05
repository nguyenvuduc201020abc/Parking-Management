package com.example.vehiclemanagemennt.UI.Login;

import com.example.vehiclemanagemennt.UI.Base.MvpPresenter;

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter {
    void startLogin(String emailId);
}
