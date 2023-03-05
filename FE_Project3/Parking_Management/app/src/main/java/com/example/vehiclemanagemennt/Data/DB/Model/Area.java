package com.example.vehiclemanagemennt.Data.DB.Model;

public class Area {
    private String mNameArea;
    private int mNoPark;
    private int mNoVacancy;

    public Area(String name, int number, int mNoVacancy) {
        this.mNameArea = name;
        this.mNoPark = number;
        this.mNoVacancy = mNoVacancy;
    }

    public String getmNameArea() {
        return mNameArea;
    }

    public void setmNameArea(String mNameArea) {
        this.mNameArea = mNameArea;
    }

    public int getmNoPark() {
        return mNoPark;
    }

    public void setmNoPark(int mNoPark) {
        this.mNoPark = mNoPark;
    }

    public int getmNoVacancy() {
        return mNoVacancy;
    }

    public void setmNoVacancy(int mNoVacancy) {
        this.mNoVacancy = mNoVacancy;
    }
}
