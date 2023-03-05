package com.example.vehiclemanagemennt.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckAccountValid {
    @JsonProperty("isValid")
    @Expose
    private boolean isValid;
    public CheckAccountValid() {
    }

    public CheckAccountValid(boolean isValid) {
        super();
        this.isValid = isValid;
    }

    public boolean isAccountValid() {
        return this.isValid;
    }
}
