package com.example.vehiclemanagemennt.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleInfo {
    @SerializedName("entryTime")
    @Expose
    private String entryTime;

    @SerializedName("licenseVehicle")
    @Expose
    private String licenseVehicle;

    @SerializedName("typeVehicle")
    @Expose
    private String typeVehicle;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("image")
    @Expose
    private String image;



    public String getImage() {
        return image;
    }

    /*public VehicleInfo(String entryTime, String licenseVehicle, String typeVehicle, String username, String color, String image, String locate) {
        this.entryTime = entryTime;
        this.licenseVehicle = licenseVehicle;
        this.typeVehicle = typeVehicle;
        this.username = username;
        this.color = color;
        this.image = image;
        this.locate = locate;
    }*/

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("locate")
    @Expose
    private String locate;

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

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

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }
}
