package com.example.vehiclemanagemennt.UI.Map;

import com.example.vehiclemanagemennt.UI.Base.MvpPresenter;

import java.util.List;

public interface MapMvpPresenter<V extends MapMvpView> extends MvpPresenter<V> {
    void createMap(List<String> mListPostion);
}
