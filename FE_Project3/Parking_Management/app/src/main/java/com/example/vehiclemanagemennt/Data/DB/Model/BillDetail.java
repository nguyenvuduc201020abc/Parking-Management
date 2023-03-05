package com.example.vehiclemanagemennt.Data.DB.Model;

public class BillDetail {
    private int billId;
    private String license;
    private String username;
    private String entry;
    private String out;
    private int cost;

    public BillDetail(int billId, String license, String username, String entry, String out, int cost) {
        this.billId = billId;
        this.license = license;
        this.username = username;
        this.entry = entry;
        this.out = out;
        this.cost = cost;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
