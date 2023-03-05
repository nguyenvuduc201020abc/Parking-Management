package com.example.vehiclemanagemennt.UI.AreaDetail;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.UI.Base.BasePresenter;
import com.example.vehiclemanagemennt.UI.Main.MainMvpPresenter;
import com.example.vehiclemanagemennt.UI.Main.MainMvpView;

public class AreaDetailPresenter<V extends AreaDetailMvpView> extends BasePresenter<V> implements AreaDetailMvpPresenter<V> {
    public AreaDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
