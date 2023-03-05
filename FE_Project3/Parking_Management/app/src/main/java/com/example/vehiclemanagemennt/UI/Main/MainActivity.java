package com.example.vehiclemanagemennt.UI.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vehiclemanagemennt.Data.DB.Model.Area;
import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.Data.Network.HttpClient;
import com.example.vehiclemanagemennt.Data.Network.Model.AreaInfo;
import com.example.vehiclemanagemennt.Data.Transaction.DataTransaction;
import com.example.vehiclemanagemennt.Data.Transaction.OnDoneCreateMapListener;
import com.example.vehiclemanagemennt.MvpApp;
import com.example.vehiclemanagemennt.R;
import com.example.vehiclemanagemennt.UI.Adapter.GridAdapter;
import com.example.vehiclemanagemennt.UI.AllBill.AllBillFragment;
import com.example.vehiclemanagemennt.UI.AreaDetail.AreaDetailActivity;
import com.example.vehiclemanagemennt.UI.Bill.BillFragment;
import com.example.vehiclemanagemennt.UI.Income.IncomeFragment;
import com.example.vehiclemanagemennt.UI.Login.LoginActivity;
import com.example.vehiclemanagemennt.UI.Map.MapFragment;
import com.example.vehiclemanagemennt.UI.Vehicle.VehicleFragment;
import com.example.vehiclemanagemennt.Utils.Constants;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity  extends AppCompatActivity implements MainMvpView, NavigationView.OnNavigationItemSelectedListener, OnDoneCreateMapListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private MainPresenter mainPresenter;
    private DrawerLayout dlNavigationBar;
    private ImageView mMenuOption;
    private DataManager dataManager;
    private TextView header;
    private List<Area> mListArea;
    private GridView gridView;
    private FrameLayout flGrid;
    private NavigationView navigationView;
    private BillFragment billFragment;
    private IncomeFragment incomeFragment;
    private AllBillFragment allBillFragment;
    private VehicleFragment vehicleFragment;
    private MapFragment mapFragment;
    private MenuItem checkOut, income, allBill, vehicle, createMap;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataTransaction.getInstance().registerOnDoneCreateMapListener(this);
        dataManager = ((MvpApp) getApplication()).getDataManager();
        mainPresenter = new MainPresenter(dataManager);
        mainPresenter.onAttach(this);
        header = findViewById(R.id.txt_welcome);
        mListArea = new ArrayList<Area>();
        flGrid = findViewById(R.id.grid_container);
        //getSupportActionBar().hide();

        navigationView = findViewById(R.id.navigation_view);
        Menu menu =navigationView.getMenu();
        checkOut = menu.findItem(R.id.check_out_nav);
        income =  menu.findItem(R.id.bill_nav);
        allBill =  menu.findItem(R.id.bill_user_nav);
        vehicle = menu.findItem(R.id.vehicle_info_nav);
        createMap = menu.findItem(R.id.create_map_nav);
        if (dataManager.isLoginAsManager()) {
            vehicle.setVisible(false);
            allBill.setVisible(false);
        } else {
            createMap.setVisible(false);
            income.setVisible(false);
            checkOut.setVisible(false);
        }
        navigationView.setNavigationItemSelectedListener(this);


        gridView = findViewById(R.id.grid_view);
        dlNavigationBar = findViewById(R.id.drawableLayout);
        mMenuOption = findViewById(R.id.ic_menu_option);

        mMenuOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlNavigationBar.openDrawer(GravityCompat.START);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: i = " + i + " : "+ mListArea.get(i).getmNameArea());
                dataManager.setAreaName(mListArea.get(i).getmNameArea());
                Intent intent = AreaDetailActivity.getStartIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        getParking();

    }

    private void getParking() {
        HttpClient httpClient = new HttpClient(Constants.API_LINK);
        httpClient.getHttpAPI().getParkingInfo().enqueue(new Callback<List<AreaInfo>>() {
            @Override
            public void onResponse(Call<List<AreaInfo>> call, Response<List<AreaInfo>> response) {
                Log.d(TAG, "onResponse: hehehhe");
                if (response.code() == 200) {
                    for (AreaInfo areaInfo : response.body() ) {
                        Log.d(TAG, "onResponse: " + areaInfo.getArea());
                        Area area = new Area(areaInfo.getArea(), areaInfo.getNumberOfParking(), areaInfo.getVacancy());
                        mListArea.add(area);
                    }
                    if (mListArea != null) {
                        GridAdapter gridAdapter = new GridAdapter(MainActivity.this, mListArea);
                        gridView.setAdapter(gridAdapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<AreaInfo>> call, Throwable t) {
                Log.d(TAG, "onFailure: hjhjhjhjhj");
            }
        });
    }

    @Override
    public void openSplashActivity() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        dlNavigationBar.closeDrawer(GravityCompat.START);
        if (id == R.id.check_out_nav) {
            Log.d(TAG, "onNavigationItemSelected: check out");
            billFragment = new BillFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.grid_container, billFragment)
                    .addToBackStack(null)
                    .commit();
            gridView.setVisibility(View.GONE);
            header.setText("HOÁ ĐƠN");
        } else if (id == R.id.bill_nav) {
            Log.d(TAG, "onNavigationItemSelected: bill");
            incomeFragment = new IncomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.grid_container, incomeFragment)
                    .addToBackStack(null)
                    .commit();
            gridView.setVisibility(View.GONE);
            header.setText("DOANH THU");
        } else if (id == R.id.main_nav) {
            getParking();
            if (billFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .remove(billFragment)
                        .commit();
            } else if (incomeFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .remove(incomeFragment)
                        .commit();
            } else if (allBillFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .remove(allBillFragment)
                        .commit();
            } else if (vehicleFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .remove(vehicleFragment)
                        .commit();
            } else if (mapFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .remove(mapFragment)
                        .commit();
            }
            gridView.setVisibility(View.VISIBLE);
            header.setText("WELCOME");
        } else if (id == R.id.bill_user_nav) {
            allBillFragment = new AllBillFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.grid_container, allBillFragment)
                    .addToBackStack(null)
                    .commit();
            gridView.setVisibility(View.GONE);
            header.setText("HOÁ ĐƠN");
        } else if (id == R.id.vehicle_info_nav) {
            vehicleFragment = new VehicleFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.grid_container, vehicleFragment)
                    .addToBackStack(null)
                    .commit();
            gridView.setVisibility(View.GONE);
            header.setText("THÔNG TIN");
        } else if (id == R.id.log_out_nav) {
            Intent intent = LoginActivity.getStartIntent(this);
            startActivity(intent);
            finish();
        } else if (id == R.id.create_map_nav) {
            mapFragment = new MapFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.grid_container, mapFragment)
                    .addToBackStack(null)
                    .commit();
            gridView.setVisibility(View.GONE);
            header.setText("TẠO MAP");
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (dlNavigationBar.isDrawerOpen(GravityCompat.START)) {
            dlNavigationBar.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public DataManager getDataManager() {
        return this.dataManager;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataTransaction.getInstance().unregisterOnDoneCreateMapListener(this);
    }

    @Override
    public void onDone() {
        gridView.setVisibility(View.VISIBLE);
        header.setText("WELCOME");
        mListArea.clear();
        getParking();
    }
}
