package com.example.vehiclemanagemennt.Data.Network.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillInfo {
    @SerializedName("billId")
    @Expose
    private int billId;

    @SerializedName("licenseVehicle")
    @Expose
    private String licenseVehicle;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("entryTime")
    @Expose
    private String entryTime;

    @SerializedName("outTime")
    @Expose
    private String outTime;

    @SerializedName("cost")
    @Expose
    private int cost;


    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getLicenseVehicle() {
        return licenseVehicle;
    }

    public void setLicenseVehicle(String licenseVehicle) {
        this.licenseVehicle = licenseVehicle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
