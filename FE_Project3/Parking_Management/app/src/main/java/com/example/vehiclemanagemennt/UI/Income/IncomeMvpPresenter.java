package com.example.vehiclemanagemennt.UI.Income;

import com.example.vehiclemanagemennt.UI.Base.MvpPresenter;
import com.example.vehiclemanagemennt.UI.Bill.BillMvpView;

public interface IncomeMvpPresenter<V extends IncomeMvpView> extends MvpPresenter<V> {
    void calculateIncome(String from, String to);
}
