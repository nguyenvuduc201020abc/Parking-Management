package com.example.vehiclemanagemennt.UI.Income;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.Data.Network.HttpClient;
import com.example.vehiclemanagemennt.Data.Network.Model.BillInfo;
import com.example.vehiclemanagemennt.Data.Network.Model.IncomeInfo;
import com.example.vehiclemanagemennt.R;
import com.example.vehiclemanagemennt.UI.Bill.BillFragment;
import com.example.vehiclemanagemennt.UI.Bill.BillMvpView;
import com.example.vehiclemanagemennt.UI.Bill.BillPresenter;
import com.example.vehiclemanagemennt.UI.Main.MainActivity;
import com.example.vehiclemanagemennt.Utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IncomeFragment extends Fragment implements IncomeMvpView {
    public static final String TAG = IncomeFragment.class.getSimpleName();
    private DataManager dataManager;
    private EditText edtFromDay, edtToDay;
    private TextView tvCost, tvNoVehicle, tvButton;
    private IncomePresenter incomePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income, container, false);
        if (dataManager != null) {
            incomePresenter = new IncomePresenter(dataManager);
            incomePresenter.onAttach(this);
        } else {
            Log.d(TAG, "onCreateView: huhuhuhuhu");
        }
        edtFromDay = view.findViewById(R.id.edt_from_day);
        edtToDay = view.findViewById(R.id.edt_to_day);
        tvCost = view.findViewById(R.id.tv_cost);
        tvNoVehicle = view.findViewById(R.id.tv_number_vehicle);
        tvButton = view.findViewById(R.id.tv_button);
        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incomePresenter.calculateIncome(edtFromDay.getText().toString(), edtToDay.getText().toString());
            }
        });
        return view;
    }


    @Override
    public void calculateIncome(String from, String to) {
        HttpClient httpClient = new HttpClient(Constants.API_LINK);
        httpClient.getHttpAPI().getIncome(from, to).enqueue(new Callback<List<IncomeInfo>>() {
            @Override
            public void onResponse(Call<List<IncomeInfo>> call, Response<List<IncomeInfo>> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: yes");
                    for (IncomeInfo billInfo : response.body()) {
                        tvCost.setText(Integer.toString(billInfo.getRevenue()));
                        tvNoVehicle.setText(Integer.toString(billInfo.getNumber_vehicle()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<IncomeInfo>> call, Throwable t) {
                Log.d(TAG, "onFailure: income fail");
            }
        });
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