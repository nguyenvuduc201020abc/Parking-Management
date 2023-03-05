package com.example.parking_management.Model;

public class ParkingMap extends Parking{
    Boolean choosen;

    public Boolean isChoosen() {
        return choosen;
    }

    public void setChoosen(Boolean choosen) {
        this.choosen = choosen;
    }

    public ParkingMap(String locate, String area, Boolean status, Boolean choosen) {
        super(locate, area, status);
        this.choosen = choosen;
    }
}
