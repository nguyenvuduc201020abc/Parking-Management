package com.example.vehiclemanagemennt.UI.ParkingInfo;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.UI.Base.BasePresenter;

public class ParkingPresenter<V extends ParkingMvpView> extends BasePresenter<V> implements ParkingMvpPresenter<V> {

    public ParkingPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void saveButton(String license, String type, String username, String color, String image, String local) {
        getMvpView().saveVehicle(license, type, username, color, image, local);
    }

    @Override
    public void cancelButton() {
        getMvpView().cancel();
    }
}
