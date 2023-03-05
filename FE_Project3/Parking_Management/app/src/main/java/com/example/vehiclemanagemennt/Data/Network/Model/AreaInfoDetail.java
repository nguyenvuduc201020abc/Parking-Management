package com.example.vehiclemanagemennt.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AreaInfoDetail {
    @SerializedName("locate")
    @Expose
    private String locate;

    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("choosen")
    @Expose
    private boolean choosen;

    public boolean isChoosen() {
        return choosen;
    }

    public void setChoosen(boolean choosen) {
        this.choosen = choosen;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
