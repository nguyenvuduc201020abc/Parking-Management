package com.example.vehiclemanagemennt.UI.ParkingInfo;

import com.example.vehiclemanagemennt.UI.Base.MvpView;

public interface ParkingMvpView extends MvpView {
    void saveVehicle(String license, String type, String username, String color, String image, String local);
    void cancel();
}
