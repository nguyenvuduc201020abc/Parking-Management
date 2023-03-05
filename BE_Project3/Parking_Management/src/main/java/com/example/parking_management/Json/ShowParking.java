package com.example.parking_management.Json;

public class ShowParking {
    private String area;
    private int vacancy;
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

    public ShowParking() {
    }

    public ShowParking(String area, int vacancy, int numberOfParking) {
        this.area = area;
        this.vacancy = vacancy;
        this.numberOfParking = numberOfParking;
    }
}
