package com.example.parking_management.Json;

import com.example.parking_management.Model.Bill;

public class ValidBill {
    private Bill bill;
    private Boolean isValid;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public ValidBill() {
    }

    public ValidBill(Bill bill, Boolean isValid) {
        this.bill = bill;
        this.isValid = isValid;
    }
}
