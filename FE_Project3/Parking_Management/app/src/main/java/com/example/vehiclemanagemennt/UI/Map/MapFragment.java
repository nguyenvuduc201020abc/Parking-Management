package com.example.vehiclemanagemennt.UI.Map;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclemanagemennt.Data.DB.Model.AreaDetail;
import com.example.vehiclemanagemennt.Data.DB.Model.BillDetail;
import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.Data.Network.HttpClient;
import com.example.vehiclemanagemennt.Data.Network.Model.BillInfo;
import com.example.vehiclemanagemennt.Data.Network.Model.CheckAccountValid;
import com.example.vehiclemanagemennt.Data.Transaction.DataTransaction;
import com.example.vehiclemanagemennt.R;
import com.example.vehiclemanagemennt.UI.Adapter.DetailAreaAdapter;
import com.example.vehiclemanagemennt.UI.Adapter.MapAdapter;
import com.example.vehiclemanagemennt.UI.Main.MainActivity;
import com.example.vehiclemanagemennt.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements MapMvpView {
    public static final String TAG = MapFragment.class.getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView findIcon;
    private EditText edtNameArea;
    private MapPresenter mapPresenter;
    private DataManager dataManager;
    private RecyclerView rvListItem;
    private TextView tvCost, tvUser, tvBillId, tvEntry, tvOut;
    private ArrayList<AreaDetail> mListAreaDetail;
    private MapAdapter mapAdapter;


    public MapFragment() {
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
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
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
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        if (dataManager != null) {
            mapPresenter = new MapPresenter(dataManager);
            mapPresenter.onAttach(this);
        } else {
            Log.d(TAG, "onCreateView: huhuhuhuhu");
        }
        setData();

        findIcon = view.findViewById(R.id.tv_button);
        rvListItem = view.findViewById(R.id.recyclerView);
        edtNameArea = view.findViewById(R.id.editTextTextPersonName);

        mapAdapter = new MapAdapter(getContext(), mListAreaDetail, new MapAdapter.OnClickItemListener() {
            @Override
            public void onItemListener(AreaDetail areaDetail) {
                if (!areaDetail.isChosen()) {
                    areaDetail.setChoose(true);
                } else {
                    areaDetail.setChoose(false);
                }
            }
        });
        mapAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.ALLOW);
        GridLayoutManager glmEmoji =
                new GridLayoutManager(getContext(), 4);
        glmEmoji.setOrientation(RecyclerView.VERTICAL);
        rvListItem.setLayoutManager(glmEmoji);
        rvListItem.setAdapter(mapAdapter);

        findIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameOfArea = edtNameArea.getText().toString();
                List<String> mListArea = new ArrayList<>();
                for (int i = 0; i < mListAreaDetail.size(); i++) {
                    if (mListAreaDetail.get(i).isChosen()) {
                        mListArea.add(nameOfArea + (i+1));
                    }

                }
                if (!TextUtils.equals(edtNameArea.getText().toString(),"")) {
                    mapPresenter.createMap(mListArea);
                } else {
                    Toast.makeText(getContext(), "Hãy nhập tên khu", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    private void setData() {
        mListAreaDetail = new ArrayList<>();
        for (int i = 1; i < 17; i++) {
            AreaDetail areaDetail = new AreaDetail(""+ i, false);
            areaDetail.setChoose(false);
            mListAreaDetail.add(areaDetail);
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            Log.d(TAG, "onAttach: run ing attach");
            dataManager =  ((MainActivity) getActivity()).getDataManager();
        }
    }

    @Override
    public void createMap(List<String> mListPosition) {
        HttpClient httpClient = new HttpClient(Constants.API_LINK);
        int cnt = 0;
        for (int i = 0; i < mListPosition.size(); i++) {
            Log.d(TAG, "createMap: i = " + i);
            httpClient.getHttpAPI().addMap(mListPosition.get(i), edtNameArea.getText().toString()).enqueue(new Callback<CheckAccountValid>() {
                @Override
                public void onResponse(Call<CheckAccountValid> call, Response<CheckAccountValid> response) {
                    boolean isValid = response.body().isAccountValid();
                    Log.d(TAG, "onResponse: isValid: " + isValid);
                    if (isValid){
                        Log.d(TAG, "onResponse: successfull ");
                    }
                }

                @Override
                public void onFailure(Call<CheckAccountValid> call, Throwable t) {
                    Log.d(TAG, "onFailure: faill");
                    Toast.makeText(getContext(), "Tạo map không thành công", Toast.LENGTH_SHORT).show();
                    return;
                }
            });
        }
        Toast.makeText(getContext(), "Tạo map thành công", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction()
                .remove(this)
                .commit();
        DataTransaction.getInstance().pushOnDoneCreateMapListener();

    }
}