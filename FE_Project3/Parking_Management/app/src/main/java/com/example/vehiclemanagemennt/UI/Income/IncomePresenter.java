package com.example.vehiclemanagemennt.UI.Income;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.UI.Base.BasePresenter;
import com.example.vehiclemanagemennt.UI.Bill.BillMvpPresenter;
import com.example.vehiclemanagemennt.UI.Bill.BillMvpView;

public class IncomePresenter<V extends IncomeMvpView> extends BasePresenter<V> implements IncomeMvpPresenter<V> {


    public IncomePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void calculateIncome(String from, String to) {
        getMvpView().calculateIncome(from, to);
    }
}
