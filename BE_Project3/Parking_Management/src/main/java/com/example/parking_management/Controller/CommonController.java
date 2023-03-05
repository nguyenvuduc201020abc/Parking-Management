package com.example.parking_management.Controller;

import com.example.parking_management.Dao.ParkingDao;
import com.example.parking_management.Dao.ParkingDaoImpl;
import com.example.parking_management.Dao.VehicleDao;
import com.example.parking_management.Dao.VehicleDaoImpl;
import com.example.parking_management.Json.CheckValid;
import com.example.parking_management.Json.ShowParking;
import com.example.parking_management.Model.Parking;
import com.example.parking_management.Model.ParkingMap;
import com.example.parking_management.Model.Vehicle;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@ResponseBody
public class CommonController {
    VehicleDao vehicleDaoImpl = new VehicleDaoImpl();
    ParkingDao parkingDaoImpl = new ParkingDaoImpl();
    Gson gson = new Gson();

        @GetMapping("/get-parking-area")
        public String getParkingArea(@RequestParam String area) throws SQLException, ClassNotFoundException {
        ArrayList<Parking> parkings = parkingDaoImpl.getParking(area);
        ArrayList<ParkingMap> parkingMaps = new ArrayList<ParkingMap>();
        for(int i=1; i<=16;i++){
            parkingMaps.add(new ParkingMap(area+i, area, false, false));
        }
        String result = "[";
        int length = parkingMaps.size();
        for (int i = 0; i < length; i++) {
            if (i != length - 1) {
                for(Parking p : parkings){
                    if(p.getLocate().equals(parkingMaps.get(i).getLocate())){
                        parkingMaps.get(i).setStatus(p.getStatus());
                        parkingMaps.get(i).setChoosen(true);
                    }
                }
                result += gson.toJson(parkingMaps.get(i));
                result += ", ";
            } else {
                for(Parking p : parkings){
                    if(p.getLocate().equals(parkingMaps.get(i).getLocate())){
                        parkingMaps.get(i).setStatus(p.getStatus());
                        parkingMaps.get(i).setChoosen(true);
                    }
                }
                result += gson.toJson(parkingMaps.get(i));
            }
        }
        return result+"]";
    }

//    @GetMapping("/get-parking")
//    public String getParking() throws SQLException, ClassNotFoundException {
//        ArrayList<Parking> parkingsA = parkingDaoImpl.getParking("A");
//        ArrayList<Parking> parkingsB = parkingDaoImpl.getParking("B");
//        ArrayList<Parking> parkingsC = parkingDaoImpl.getParking("C");
//        ArrayList<Parking> parkingsD = parkingDaoImpl.getParking("D");
//        int vacancy = 0;
//        for (Parking parking : parkingsA) {
//            if (parking.getStatus() == false) {
//                vacancy++;
//            }
//        }
//        ShowParking showParkingA = new ShowParking("A", vacancy, parkingsA.size());
//        vacancy = 0;
//        for (Parking parking : parkingsB) {
//            if (parking.getStatus() == false) {
//                vacancy++;
//            }
//        }
//        ShowParking showParkingB = new ShowParking("B", vacancy, parkingsB.size());
//        vacancy = 0;
//        for (Parking parking : parkingsC) {
//            if (parking.getStatus() == false) {
//                vacancy++;
//            }
//        }
//        ShowParking showParkingC = new ShowParking("C", vacancy, parkingsC.size());
//        vacancy = 0;
//        for (Parking parking : parkingsD) {
//            if (parking.getStatus() == false) {
//                vacancy++;
//            }
//        }
//        ShowParking showParkingD = new ShowParking("D", vacancy, parkingsD.size());
//        String result = "";
//        result = result + "["+ gson.toJson(showParkingA) + ", " + gson.toJson(showParkingB) + ", " + gson.toJson(showParkingC) + ", " + gson.toJson(showParkingD) + "]";
//        return result;
//    }
    @PostMapping("/save-vehicle")
    public String saveVehicle(@RequestParam String licenseVehicle, @RequestParam String typeVehicle, @RequestParam String username , @RequestParam String color, @RequestParam String image, @RequestParam String locate) throws SQLException, ClassNotFoundException {
        CheckValid checkValid = new CheckValid();
        Vehicle vehicle = new Vehicle(licenseVehicle, typeVehicle, username, color, image, locate);
        checkValid.setValid(vehicleDaoImpl.addVehicle(vehicle));
        return gson.toJson(checkValid);
    }
    @GetMapping("/get-parking")
    public String getParking() throws SQLException, ClassNotFoundException {
        List<String> listArea = parkingDaoImpl.getArea();
        int vacancy = 0;
        String result = "[";
        int index = 0;
        for(String area : listArea){
            index++;
            int cnt = listArea.size();
            ArrayList<Parking> parkings = parkingDaoImpl.getParking(area);
            for (Parking parking : parkings) {
                if (parking.getStatus() == false) {
                    vacancy++;
                }
            }
            ShowParking showParking = new ShowParking(area, vacancy, parkings.size());
            vacancy = 0;
            if(index!=cnt) {
                result += gson.toJson(showParking)+", ";
            }
            else{
                result += gson.toJson(showParking)+"]";
            }
        }
        return result;
    }
}


