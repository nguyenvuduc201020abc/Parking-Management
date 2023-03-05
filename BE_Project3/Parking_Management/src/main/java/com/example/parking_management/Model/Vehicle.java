package com.example.parking_management.Model;

import java.sql.Date;

public class Vehicle {
    private String licenseVehicle;
    private String typeVehicle;
    private String username;
    private String color;
    private String image;
    private String locate;

    public String getLicenseVehicle() {
        return licenseVehicle;
    }

    public void setLicenseVehicle(String licenseVehicle) {
        this.licenseVehicle = licenseVehicle;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public Vehicle(String licenseVehicle, String typeVehicle, String username, String color, String image, String locate) {
        this.licenseVehicle = licenseVehicle;
        this.typeVehicle = typeVehicle;
        this.username = username;
        this.color = color;
        this.image = image;
        this.locate = locate;
    }

    public Vehicle() {
    }
}
