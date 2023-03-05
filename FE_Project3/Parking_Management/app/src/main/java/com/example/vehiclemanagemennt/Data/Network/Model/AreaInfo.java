package com.example.vehiclemanagemennt.Data.Network.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AreaInfo {
    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("vacancy")
    @Expose
    private int vacancy;

    @SerializedName("numberOfParking")
    @Expose
    private int numberOfParking;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public int getNumberOfParking() {
        return numberOfParking;
    }

    public void setNumberOfParking(int numberOfParking) {
        this.numberOfParking = numberOfParking;
    }
}
