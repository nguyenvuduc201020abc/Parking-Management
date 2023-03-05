package com.example.vehiclemanagemennt.UI.Map;

import com.example.vehiclemanagemennt.UI.Base.MvpView;

import java.util.List;

public interface MapMvpView extends MvpView {
    void createMap(List<String> mListPosition);
}
