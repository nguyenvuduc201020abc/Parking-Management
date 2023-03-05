package com.example.vehiclemanagemennt.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IncomeInfo {
    @SerializedName("number_vehicle")
    @Expose
    private int number_vehicle;

    @SerializedName("revenue")
    @Expose
    private int revenue;

    public int getNumber_vehicle() {
        return number_vehicle;
    }

    public void setNumber_vehicle(int number_vehicle) {
        this.number_vehicle = number_vehicle;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
}
