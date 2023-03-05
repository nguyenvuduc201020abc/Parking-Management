package com.example.vehiclemanagemennt.UI.ParkingInfo;

import com.example.vehiclemanagemennt.UI.Base.MvpPresenter;
import com.example.vehiclemanagemennt.UI.Main.MainMvpView;

public interface ParkingMvpPresenter<V extends ParkingMvpView> extends MvpPresenter<V> {
    void saveButton(String license, String type, String username, String color, String image, String local);
    void cancelButton();
}
