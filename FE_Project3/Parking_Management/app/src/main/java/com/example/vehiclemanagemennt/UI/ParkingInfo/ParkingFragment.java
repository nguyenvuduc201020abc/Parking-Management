package com.example.vehiclemanagemennt.UI.ParkingInfo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.Data.Network.HttpClient;
import com.example.vehiclemanagemennt.Data.Network.Model.CheckAccountValid;
import com.example.vehiclemanagemennt.Data.Transaction.DataTransaction;
import com.example.vehiclemanagemennt.R;
import com.example.vehiclemanagemennt.UI.AreaDetail.AreaDetailActivity;
import com.example.vehiclemanagemennt.UI.Main.MainActivity;
import com.example.vehiclemanagemennt.Utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParkingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParkingFragment extends DialogFragment implements ParkingMvpView{
    public static final String TAG = ParkingFragment.class.getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DataManager dataManager;
    private ParkingPresenter parkingPresenter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String userName, local;

    private Button btnSave, btnCancel;
    private EditText edtLicense, edtType, edtUser, edtColor, edtImage, edtLocal;

    public ParkingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ParkingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParkingFragment newInstance(String param1, String param2) {
        ParkingFragment fragment = new ParkingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(ARG_PARAM1);
            local = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment'
        View view = inflater.inflate(R.layout.fragment_parking, container, false);
        edtLicense = view.findViewById(R.id.edt_license);
        edtType = view.findViewById(R.id.edt_type);
        edtColor = view.findViewById(R.id.edt_color);
        edtImage = view.findViewById(R.id.edt_image);

        btnSave = view.findViewById(R.id.btn_ok);
        btnCancel = view.findViewById(R.id.btn_cancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveVehicle(edtLicense.getText().toString(), edtType.getText().toString(),
                        userName, edtColor.getText().toString(), edtImage.getText().toString(), local);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parkingPresenter.cancelButton();
            }
        });

        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        return view;
    }

    @Override
    public void saveVehicle(String license, String type, String username, String color, String image, String local) {
        HttpClient httpClient = new HttpClient(Constants.API_LINK);
        httpClient.getHttpAPI().saveVehicle(license, type, username, color, image, local).enqueue(new Callback<CheckAccountValid>() {
            @Override
            public void onResponse(Call<CheckAccountValid> call, Response<CheckAccountValid> response) {
                boolean isValid = response.body().isAccountValid();
                Log.d(TAG, "onResponse: isValid: " + isValid);
                if (isValid){
                    Toast.makeText(getContext(), "Save successfully", Toast.LENGTH_SHORT).show();
                    DataTransaction.getInstance().pushSaveSuccessListener(local);
                    cancel();
                } else {
                    Toast.makeText(getContext(), "Save unsuccessfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckAccountValid> call, Throwable t) {
                Log.d(TAG, "onFailure: fail");
            }   
        });
    }

    @Override
    public void cancel() {
        this.dismiss();
    }

}