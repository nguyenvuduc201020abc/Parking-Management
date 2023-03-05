package com.example.vehiclemanagemennt.UI.Main;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.UI.Base.BasePresenter;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }
    @Override
    public String getAccount() {
        return null;
    }

    @Override
    public void setUserLoggedOut() {

    }
}
