package com.example.vehiclemanagemennt.UI.Bill;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclemanagemennt.Data.DB.Model.Area;
import com.example.vehiclemanagemennt.Data.DB.Model.BillDetail;
import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.Data.Network.HttpClient;
import com.example.vehiclemanagemennt.Data.Network.Model.AreaInfo;
import com.example.vehiclemanagemennt.Data.Network.Model.BillInfo;
import com.example.vehiclemanagemennt.MvpApp;
import com.example.vehiclemanagemennt.R;
import com.example.vehiclemanagemennt.UI.Adapter.GridAdapter;
import com.example.vehiclemanagemennt.UI.Main.MainActivity;
import com.example.vehiclemanagemennt.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillFragment extends Fragment implements BillMvpView {
    public static final String TAG = BillFragment.class.getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView findIcon;
    private EditText edtLicense;
    private BillPresenter billPresenter;
    private DataManager dataManager;
    private TextView tvCost, tvUser, tvBillId, tvEntry, tvOut;

    public BillFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BillFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BillFragment newInstance(String param1, String param2) {
        BillFragment fragment = new BillFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill, container, false);
        if (dataManager != null) {
            billPresenter = new BillPresenter(dataManager);
            billPresenter.onAttach(this);
        } else {
            Log.d(TAG, "onCreateView: huhuhuhuhu");
        }
        tvBillId = (TextView) view.findViewById(R.id.tv_bill_id);
        tvCost = (TextView) view.findViewById(R.id.tv_cost);
        tvEntry = (TextView) view.findViewById(R.id.tv_entry);
        tvOut = (TextView) view.findViewById(R.id.tv_checkout);
        tvUser = (TextView) view.findViewById(R.id.tv_username);
        findIcon = view.findViewById(R.id.imageView8);
        edtLicense = view.findViewById(R.id.edt_license_bill);
        findIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billPresenter.findBillOfVehicle(edtLicense.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void findBillVehicle(String license) {
        ArrayList<BillDetail> mListBill = new ArrayList<>();
        HttpClient httpClient = new HttpClient(Constants.API_LINK);
        httpClient.getHttpAPI().getBillVehicleInfo(license).enqueue(new Callback<List<BillInfo>>() {
            @Override
            public void onResponse(Call<List<BillInfo>> call, Response<List<BillInfo>> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: success");
                    for (BillInfo billInfo : response.body()) {
                        if (billInfo != null) {
                            BillDetail bill = new BillDetail(billInfo.getBillId(), billInfo.getLicenseVehicle(), billInfo.getUsername()
                                    , billInfo.getEntryTime(), billInfo.getOutTime(), billInfo.getCost());
                            mListBill.add(bill);
                        } else {
                            Toast.makeText(getContext(), "Không tìm thấy biển số xe", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if (mListBill.size() > 0) {
                        tvCost.setText(Integer.toString(mListBill.get(0).getCost()));
                        tvBillId.setText(Integer.toString(mListBill.get(0).getBillId()));
                        tvEntry.setText(mListBill.get(0).getEntry());
                        tvOut.setText(mListBill.get(0).getOut());
                        tvUser.setText(mListBill.get(0).getUsername());
                    } else {
                        tvCost.setText("NaN");
                        tvBillId.setText("NaN");
                        tvEntry.setText("NaN");
                        tvOut.setText("NaN");
                        tvUser.setText("NaN");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BillInfo>> call, Throwable t) {
                Log.d(TAG, "onFailure: fail");
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