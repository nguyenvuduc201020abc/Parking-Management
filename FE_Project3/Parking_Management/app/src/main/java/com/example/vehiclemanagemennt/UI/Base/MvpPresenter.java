package com.example.vehiclemanagemennt.UI.Base;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
}
