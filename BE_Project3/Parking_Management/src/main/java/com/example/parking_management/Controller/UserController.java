package com.example.parking_management.Controller;

import com.example.parking_management.Dao.*;

import com.example.parking_management.Json.CheckValid;
import com.example.parking_management.Model.Bill;
import com.example.parking_management.Model.VehicleInParking;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@ResponseBody
public class  UserController {
    UserDao userDaoImpl = new UserDaoImpl();
    ParkingDao parkingDaoImpl = new ParkingDaoImpl();

    Gson gson = new Gson();

    @GetMapping("/login-user")
    public String Login(@RequestParam String username, @RequestParam String password) throws SQLException, ClassNotFoundException {
        CheckValid checkValid = new CheckValid();
        checkValid.setValid(userDaoImpl.checkUser(username, password));
        return gson.toJson(checkValid);
    }
    @PostMapping("/signup-user")
    public String Signup(@RequestParam String username, @RequestParam String password) throws SQLException, ClassNotFoundException {
        CheckValid checkValid = new CheckValid();
        checkValid.setValid(userDaoImpl.addUser(username, password));
        return gson.toJson(checkValid);
    }
    @GetMapping("/get-bill-user")
    public String GetBill(@RequestParam String username) throws SQLException, ClassNotFoundException{
        List<Bill> bills = userDaoImpl.getBillByUsername(username);
        String result = "[";
        for( int i=0;i<bills.size();i++){
            result+=gson.toJson(bills.get(i));
            if(i!=bills.size()-1) result+=", ";
        }
        result+="]";
        return result;
    }
        @GetMapping("/get-vehicle-user")
    public String GetVehicleUser(@RequestParam String username) throws SQLException, ClassNotFoundException{
        List<VehicleInParking> vehicleInParkings = userDaoImpl.getVehicleByUsername(username);
        String result = "[";
        for( int i=0;i<vehicleInParkings.size();i++){
            result+=gson.toJson(vehicleInParkings.get(i));
            if(i!=vehicleInParkings.size()-1) result+=", ";
        }
        result+="]";
        return result;
    }
}
