package com.example.vehiclemanagemennt.UI.Map;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.UI.Base.BasePresenter;

import java.util.List;

public class MapPresenter<V extends MapMvpView> extends BasePresenter<V> implements MapMvpPresenter<V> {
    public MapPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void createMap(List<String> license) {
        getMvpView().createMap(license);
    }
}
