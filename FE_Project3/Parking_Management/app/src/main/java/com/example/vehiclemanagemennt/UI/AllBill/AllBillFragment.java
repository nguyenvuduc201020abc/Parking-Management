package com.example.vehiclemanagemennt.UI.AllBill;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vehiclemanagemennt.Data.DB.Model.BillDetail;
import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.Data.Network.HttpClient;
import com.example.vehiclemanagemennt.Data.Network.Model.BillInfo;
import com.example.vehiclemanagemennt.R;
import com.example.vehiclemanagemennt.UI.Adapter.BillInfoAdapter;
import com.example.vehiclemanagemennt.UI.Adapter.DetailAreaAdapter;
import com.example.vehiclemanagemennt.UI.Bill.BillFragment;
import com.example.vehiclemanagemennt.UI.Bill.BillMvpView;
import com.example.vehiclemanagemennt.UI.Bill.BillPresenter;
import com.example.vehiclemanagemennt.UI.Main.MainActivity;
import com.example.vehiclemanagemennt.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllBillFragment extends Fragment implements AllBillMvpView {
    public static final String TAG = AllBillFragment.class.getSimpleName();
    private DataManager dataManager;
    private AllBillPresenter billPresenter;
    private List<BillDetail> mListBill;
    private RecyclerView rvListItem;
    private BillInfoAdapter detailBillAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_bill, container, false);
        if (dataManager != null) {
            billPresenter = new AllBillPresenter(dataManager);
            billPresenter.onAttach(this);
        }
        rvListItem = view.findViewById(R.id.recyclerView);
        mListBill = new ArrayList<>();
        HttpClient httpClient = new HttpClient(Constants.API_LINK);
        Log.d(TAG, "onCreateView: user name " + dataManager.getEmailId());
        httpClient.getHttpAPI().getBillOfUser(dataManager.getEmailId()).enqueue(new Callback<List<BillInfo>>() {
            @Override
            public void onResponse(Call<List<BillInfo>> call, Response<List<BillInfo>> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: success");
                    for (BillInfo billInfo : response.body()) {
                        BillDetail bill = new BillDetail(billInfo.getBillId(), billInfo.getLicenseVehicle(), billInfo.getUsername()
                                , billInfo.getEntryTime(), billInfo.getOutTime(), billInfo.getCost());
                        mListBill.add(bill);
                    }
                    if (mListBill != null) {
                        detailBillAdapter = new BillInfoAdapter(getContext(), mListBill);
                        GridLayoutManager glmEmoji =
                                new GridLayoutManager(getContext(), 1);
                        glmEmoji.setOrientation(RecyclerView.VERTICAL);
                        rvListItem.setLayoutManager(glmEmoji);
                        rvListItem.setAdapter(detailBillAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BillInfo>> call, Throwable t) {
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