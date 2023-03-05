package com.example.vehiclemanagemennt.UI.AllBill;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.UI.Base.BasePresenter;
import com.example.vehiclemanagemennt.UI.Bill.BillMvpPresenter;
import com.example.vehiclemanagemennt.UI.Bill.BillMvpView;

public class AllBillPresenter<V extends AllBillMvpView> extends BasePresenter<V> implements AllBillMvpPresenter<V> {

    public AllBillPresenter(DataManager dataManager) {
        super(dataManager);
    }

}
