package com.example.vehiclemanagemennt.UI.Base;

import com.example.vehiclemanagemennt.Data.DataManager;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V>{
    private DataManager mDataManager;
    private V mMvpView;

    public BasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mMvpView = mvpView;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

}
