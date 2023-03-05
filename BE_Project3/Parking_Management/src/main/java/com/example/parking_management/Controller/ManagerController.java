package com.example.parking_management.Controller;

import com.example.parking_management.Dao.ManagerDao;
import com.example.parking_management.Dao.ManagerDaoImpl;
import com.example.parking_management.Json.CheckValid;
import com.example.parking_management.Json.Statistic;
import com.example.parking_management.Json.ValidBill;
import com.example.parking_management.Model.Bill;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@RestController
@ResponseBody
public class ManagerController {
    ManagerDao managerDaoImpl = new ManagerDaoImpl();
    Gson gson = new Gson();
    @GetMapping("/login-manager")
    public String Login(@RequestParam String username, @RequestParam String password) throws SQLException, ClassNotFoundException {
        CheckValid checkValid = new CheckValid();
        checkValid.setValid(managerDaoImpl.checkManager(username, password));
        return gson.toJson(checkValid);
    }
    @GetMapping("/export-bill")
    public String ExportBill(@RequestParam String licenseVehicle) throws SQLException, ClassNotFoundException{
        Bill bill = managerDaoImpl.exportBill(licenseVehicle);
        return "["+gson.toJson(bill)+"]";
    }
    @GetMapping("/statistic")
    public String statisticByTime(@RequestParam Date from, @RequestParam Date to)throws SQLException, ClassNotFoundException{
        Statistic statistic = managerDaoImpl.statisticByTime(from, to);
        return "["+gson.toJson(statistic)+ "]";
    }

    @GetMapping("/get-area")
    public String getArea()throws SQLException, ClassNotFoundException{

        List<String> listArea = managerDaoImpl.getArea();
        return gson.toJson(listArea);
    }

    @PostMapping("/add-map")
    public String AddMap(@RequestParam String locate, @RequestParam String area) throws SQLException, ClassNotFoundException {
        CheckValid checkValid = new CheckValid();
        checkValid.setValid(managerDaoImpl.addMap(locate, area));
        return gson.toJson(checkValid);
    }
}
