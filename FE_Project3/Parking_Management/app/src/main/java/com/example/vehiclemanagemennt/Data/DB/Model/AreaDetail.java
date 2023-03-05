package com.example.vehiclemanagemennt.Data.DB.Model;

public class AreaDetail {
    private String mNameArea;
    private boolean isAvailable;
    private boolean isChosen;

    public AreaDetail(String name, boolean isAvailable) {
        this.isAvailable = isAvailable;
        this.mNameArea = name;
    }

    public String getmNameArea() {
        return mNameArea;
    }

    public void setmNameArea(String mNameArea) {
        this.mNameArea = mNameArea;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isChosen() {
        return this.isChosen;
    }
    public void setChoose(boolean isChoose) {
        this.isChosen = isChoose;
    }
}
