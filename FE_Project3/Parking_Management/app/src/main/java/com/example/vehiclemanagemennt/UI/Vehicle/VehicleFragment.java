package com.example.vehiclemanagemennt.UI.Vehicle;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclemanagemennt.Data.DB.Model.BillDetail;
import com.example.vehiclemanagemennt.Data.DB.Model.VehicleDetail;
import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.Data.Network.HttpClient;
import com.example.vehiclemanagemennt.Data.Network.Model.BillInfo;
import com.example.vehiclemanagemennt.Data.Network.Model.VehicleInfo;
import com.example.vehiclemanagemennt.R;
import com.example.vehiclemanagemennt.UI.Adapter.BillInfoAdapter;
import com.example.vehiclemanagemennt.UI.Adapter.VehicleAdapter;
import com.example.vehiclemanagemennt.UI.Main.MainActivity;
import com.example.vehiclemanagemennt.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VehicleFragment extends Fragment implements VehicleMvpView {
    public static final String TAG = VehicleFragment.class.getSimpleName();
    private DataManager dataManager;
    private VehiclePresenter billPresenter;
    private List<VehicleDetail> mListBill;
    private RecyclerView rvListItem;
    private VehicleAdapter detailBillAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_bill, container, false);
        if (dataManager != null) {
            billPresenter = new VehiclePresenter(dataManager);
            billPresenter.onAttach(this);
        }
        rvListItem = view.findViewById(R.id.recyclerView);
        mListBill = new ArrayList<>();
        HttpClient httpClient = new HttpClient(Constants.API_LINK);
        Log.d(TAG, "onCreateView: user name " + dataManager.getEmailId());
        httpClient.getHttpAPI().getVehicleUser(dataManager.getEmailId()).enqueue(new Callback<List<VehicleInfo>>() {
            @Override
            public void onResponse(Call<List<VehicleInfo>> call, Response<List<VehicleInfo>> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: success");
                    for (VehicleInfo billInfo : response.body()) {
                        VehicleDetail bill = new VehicleDetail();
                        bill.setColor(billInfo.getColor());
                        bill.setEntryTime(billInfo.getEntryTime());
                        bill.setLicenseVehicle(billInfo.getLicenseVehicle());
                        bill.setLocate(billInfo.getLocate());
                        bill.setUsername(billInfo.getUsername());
                        bill.setImage(billInfo.getImage());
                        bill.setTypeVehicle(billInfo.getTypeVehicle());
                        mListBill.add(bill);
                    }
                    if (mListBill != null) {
                        detailBillAdapter = new VehicleAdapter(getContext(), mListBill);;
                        GridLayoutManager glmEmoji =
                                new GridLayoutManager(getContext(), 1);
                        glmEmoji.setOrientation(RecyclerView.VERTICAL);
                        rvListItem.setLayoutManager(glmEmoji);
                        rvListItem.setAdapter(detailBillAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VehicleInfo>> call, Throwable t) {
                Log.d(TAG, "onFailure: huhuhuhuhuhu");
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            Log.d(TAG, "onAttach: run ing attach");
            dataManager =  ((MainActivity) getActivity()).getDataManager();
        }
    }
}