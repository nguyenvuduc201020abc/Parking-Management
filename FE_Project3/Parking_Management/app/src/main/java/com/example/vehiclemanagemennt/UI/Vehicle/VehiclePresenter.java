package com.example.vehiclemanagemennt.UI.Vehicle;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.UI.Base.BasePresenter;

public class VehiclePresenter<V extends VehicleMvpView> extends BasePresenter<V> implements VehicleMvpPresenter<V> {

    public VehiclePresenter(DataManager dataManager) {
        super(dataManager);
    }

}
