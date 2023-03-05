package com.example.parking_management.Model;

import java.sql.Date;
import java.sql.Timestamp;

public class Bill {
    private int billId;
    private String licenseVehicle;
    private String username;
    private Timestamp entryTime;
    private Timestamp outTime;
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

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }

    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Bill() {
    }

    public Bill(int billId, String licenseVehicle, Timestamp entryTime, String username, Timestamp outTime, int cost) {
        this.billId = billId;
        this.licenseVehicle = licenseVehicle;
        this.username = username;
        this.entryTime = entryTime;
        this.outTime = outTime;
        this.cost = cost;
    }
}
