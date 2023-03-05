package com.example.parking_management.Json;

public class Statistic {
    private int number_vehicle;
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

    public Statistic() {
    }

    public Statistic(int number_vehicle, int revenue) {
        this.number_vehicle = number_vehicle;
        this.revenue = revenue;
    }
}
