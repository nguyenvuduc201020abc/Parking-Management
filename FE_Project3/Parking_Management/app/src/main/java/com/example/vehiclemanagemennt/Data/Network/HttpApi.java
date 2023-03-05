package com.example.vehiclemanagemennt.Data.Network;

import com.example.vehiclemanagemennt.Data.Network.Model.AreaInfoDetail;
import com.example.vehiclemanagemennt.Data.Network.Model.AreaInfo;
import com.example.vehiclemanagemennt.Data.Network.Model.BillInfo;
import com.example.vehiclemanagemennt.Data.Network.Model.CheckAccountValid;
import com.example.vehiclemanagemennt.Data.Network.Model.IncomeInfo;
import com.example.vehiclemanagemennt.Data.Network.Model.VehicleInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HttpApi {
    @GET("/login-user?")
    Call<CheckAccountValid> checkAccountValid(@Query("username") String token, @Query("password") String name);

    @GET("/login-manager?")
    Call<CheckAccountValid> checkManagerValid(@Query("username") String token, @Query("password") String name);

    @GET("/get-parking")
    Call<List<AreaInfo>> getParkingInfo();

    @GET("/get-parking-area?")
    Call<List<AreaInfoDetail>> getAreaDetailInfo(@Query("area") String area);

    @POST("/signup-user?")
    Call<CheckAccountValid> signUpAccount(@Query("username") String userName, @Query("password") String password);

    @GET("/export-bill?")
    Call<List<BillInfo>> getBillVehicleInfo(@Query("licenseVehicle") String licenseVehicle);

    @POST("/save-vehicle?")
    Call<CheckAccountValid> saveVehicle(@Query("licenseVehicle") String licenseVehicle, @Query("typeVehicle") String typeVehicle,
                                        @Query("username") String username, @Query("color") String color, @Query("image") String image,
                                        @Query("locate") String locate);

    @GET("/statistic?")
    Call<List<IncomeInfo>> getIncome(@Query("from") String fromDay, @Query("to") String toDay);

    @GET("/get-bill-user?")
    Call<List<BillInfo>> getBillOfUser(@Query("username") String username);

    @GET("/get-vehicle-user?")
    Call<List<VehicleInfo>> getVehicleUser(@Query("username") String username);

    @POST("/add-map?")
    Call<CheckAccountValid> addMap(@Query("locate") String locate, @Query("area") String area);

}
