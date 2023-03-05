package com.example.vehiclemanagemennt.UI.Bill;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.UI.Base.BasePresenter;

public class BillPresenter<V extends BillMvpView> extends BasePresenter<V> implements BillMvpPresenter<V> {
    public BillPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void findBillOfVehicle(String license) {
        getMvpView().findBillVehicle(license);
    }
}
