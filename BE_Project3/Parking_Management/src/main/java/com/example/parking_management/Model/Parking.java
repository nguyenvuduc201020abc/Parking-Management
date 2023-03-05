package com.example.parking_management.Model;

public class Parking {
    private String locate;
    private String area;
    private Boolean status;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Parking() {
    }

    public Parking(String locate, String area, Boolean status) {
        this.locate = locate;
        this.area = area;
        this.status = status;
    }
}
