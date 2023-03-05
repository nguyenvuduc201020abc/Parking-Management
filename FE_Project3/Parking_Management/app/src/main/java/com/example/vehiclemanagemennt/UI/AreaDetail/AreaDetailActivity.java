package com.example.vehiclemanagemennt.UI.AreaDetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclemanagemennt.Data.DB.Model.AreaDetail;
import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.Data.Network.HttpClient;
import com.example.vehiclemanagemennt.Data.Network.Model.AreaInfoDetail;
import com.example.vehiclemanagemennt.Data.Transaction.DataTransaction;
import com.example.vehiclemanagemennt.Data.Transaction.OnSaveSuccessListener;
import com.example.vehiclemanagemennt.MvpApp;
import com.example.vehiclemanagemennt.R;
import com.example.vehiclemanagemennt.UI.Adapter.DetailAreaAdapter;
import com.example.vehiclemanagemennt.UI.Login.LoginActivity;
import com.example.vehiclemanagemennt.UI.Main.MainActivity;
import com.example.vehiclemanagemennt.UI.ParkingInfo.ParkingFragment;
import com.example.vehiclemanagemennt.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AreaDetailActivity extends AppCompatActivity implements AreaDetailMvpView, OnSaveSuccessListener {
    public static final String TAG = AreaDetailActivity.class.getSimpleName();
    private DataManager dataManager;
    private AreaDetailPresenter areaDetailPresenter;
    private TextView tvHeader;
    private ImageView backButton;
    private RecyclerView rvListItem;
    private ArrayList<AreaDetail> mListAreaDetail;
    private String local;
    private DetailAreaAdapter detailAreaAdapter;

    /*public AreaDetail(DataManager dataManager) {
        this.dataManager = dataManager;
    }*/
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AreaDetailActivity.class);
        return intent;
    }
    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_area, container, false);
        tvHeader = (TextView) view.findViewById(R.id.txt_detail_area);
        rvListItem = view.findViewById(R.id.recyclerView);
        createList(dataManager.getAreaName());
        tvHeader.setText("KHU " + dataManager.getAreaName());
        return view;
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_area);
        //getSupportActionBar().hide();
        dataManager = ((MvpApp) getApplication()).getDataManager();
        areaDetailPresenter = new AreaDetailPresenter(dataManager);
        areaDetailPresenter.onAttach(this);
        mListAreaDetail = new ArrayList<>();
        tvHeader = (TextView) findViewById(R.id.txt_detail_area);
        rvListItem = findViewById(R.id.recyclerView);
        backButton = findViewById(R.id.back_button);
        createList(dataManager.getAreaName());
        tvHeader.setText("KHU " + dataManager.getAreaName());
        Log.d(TAG, "onCreate: khu: " + dataManager.getAreaName());
        DataTransaction.getInstance().registerSaveSuccessListener(this);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackButton();
            }
        });

    }

    private void createList(String areaName) {
        HttpClient httpClient = new HttpClient(Constants.API_LINK);
        httpClient.getHttpAPI().getAreaDetailInfo(areaName).enqueue(new Callback<List<AreaInfoDetail>>() {
            @Override
            public void onResponse(Call<List<AreaInfoDetail>> call, Response<List<AreaInfoDetail>> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: heheheheheheheh");
                    for (AreaInfoDetail areaInfo : response.body()) {
                        Log.d(TAG, "onResponse: " + areaInfo.getLocate());
                        com.example.vehiclemanagemennt.Data.DB.Model.AreaDetail areaDetail = new com.example.vehiclemanagemennt.Data.DB.Model.AreaDetail(areaInfo.getLocate(), areaInfo.isStatus());
                        areaDetail.setChoose(areaInfo.isChoosen());
                        mListAreaDetail.add(areaDetail);
                    }
                    if (mListAreaDetail != null) {
                        detailAreaAdapter = new DetailAreaAdapter(getApplication(), mListAreaDetail, new DetailAreaAdapter.OnClickItemListener() {
                            @Override
                            public void onItemListener(AreaDetail areaDetail) {
                                Log.d(TAG, "onItemListener: item: " + areaDetail.getmNameArea());
                                local = areaDetail.getmNameArea();
                                if (!areaDetail.isAvailable()) {
                                    ParkingFragment.newInstance(dataManager.getEmailId(), local).show(getSupportFragmentManager(), ParkingFragment.TAG);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Không thể cho xe vào vị trí này", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        detailAreaAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.ALLOW);
                        GridLayoutManager glmEmoji =
                                new GridLayoutManager(getApplication(), 4);
                        glmEmoji.setOrientation(RecyclerView.VERTICAL);
                        rvListItem.setLayoutManager(glmEmoji);
                        rvListItem.setAdapter(detailAreaAdapter);
                        
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AreaInfoDetail>> call, Throwable t) {
                Log.d(TAG, "onFailure: huhuuhuhuhuh");
            }
        });
    }

    public String getLocal() {
        return this.local;
    }

    public DataManager getDataManager() {
        return this.dataManager;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: 28-02");
        if (detailAreaAdapter != null) {
            detailAreaAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataTransaction.getInstance().unregisterSaveSuccessListener(this);
    }

    @Override
    public void onBackButton() {
        onBackPressed();
    }

    @Override
    public void onSaveSuccess(String locate) {
        for (int i = 0; i < mListAreaDetail.size(); i++) {
            if (TextUtils.equals(mListAreaDetail.get(i).getmNameArea(), locate)) {
                Log.d(TAG, "onSaveSuccess: locate " + locate);
                mListAreaDetail.get(i).setAvailable(true);
            }
        }
        detailAreaAdapter.notifyDataSetChanged();
    }
}
