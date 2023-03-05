package com.example.vehiclemanagemennt.UI.Bill;

import com.example.vehiclemanagemennt.UI.Base.MvpPresenter;

public interface BillMvpPresenter<V extends BillMvpView> extends MvpPresenter<V> {
    void findBillOfVehicle(String license);
}
