package com.example.vehiclemanagemennt.UI.Login;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.UI.Base.BasePresenter;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter implements LoginMvpPresenter<V> {

    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void startLogin(String emailId) {

    }
}
