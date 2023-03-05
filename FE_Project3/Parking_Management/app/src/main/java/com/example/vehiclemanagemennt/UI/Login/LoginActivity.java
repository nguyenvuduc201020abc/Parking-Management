package com.example.vehiclemanagemennt.UI.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.vehiclemanagemennt.Data.DataManager;
import com.example.vehiclemanagemennt.Data.Network.HttpClient;
import com.example.vehiclemanagemennt.Data.Network.Model.CheckAccountValid;
import com.example.vehiclemanagemennt.MvpApp;
import com.example.vehiclemanagemennt.R;
import com.example.vehiclemanagemennt.UI.Main.MainActivity;
import com.example.vehiclemanagemennt.Utils.Constants;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity implements LoginMvpView {
    public static final String TAG = LoginActivity.class.getSimpleName();
    private LoginPresenter loginPresenter;
    private EditText edtAccount, edtPassword;
    private TextView tvLogin, tvSignup;
    private Button mButton;
    private boolean isLogin;
    private HttpClient httpClient;
    private DataManager dataManager;
    private CheckBox cbAsManager;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        httpClient = new HttpClient(Constants.API_LINK);
        dataManager = ((MvpApp) getApplication()).getDataManager();
        loginPresenter = new LoginPresenter(dataManager);

        loginPresenter.onAttach(this);
        edtAccount = findViewById(R.id.edt_user_name);
        edtPassword = findViewById(R.id.edt_password);
        mButton = findViewById(R.id.button);
        tvLogin = findViewById(R.id.tv_login);
        tvSignup = findViewById(R.id.tv_sign_up);
        cbAsManager = findViewById(R.id.checkBox);
        isLogin = true;

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClick();
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSignup.setTextColor(Color.parseColor("#00A5FF"));
                tvLogin.setTextColor(Color.parseColor("#000000"));
                isLogin = false;
                cbAsManager.setVisibility(View.GONE);
            }
        });
        dataManager.setLoginAsManager(false);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSignup.setTextColor(Color.parseColor("#000000"));
                tvLogin.setTextColor(Color.parseColor("#00A5FF"));
                isLogin = true;
                cbAsManager.setVisibility(View.VISIBLE);
            }
        });

        cbAsManager.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    dataManager.setLoginAsManager(true);
                } else {
                    dataManager.setLoginAsManager(false);
                }
            }
        });

    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginButtonClick() {
        String userName = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();
        buttonFunction(userName, password);

    }
    private void buttonFunction(String userName, String password) {
        if (isLogin) {
            if (!dataManager.isLoginAsManager()) {
                Log.d(TAG, "buttonFunction: hihihihiih");
                httpClient.getHttpAPI().checkAccountValid(userName, password).enqueue(new Callback<CheckAccountValid>() {
                    @Override
                    public void onResponse(Call<CheckAccountValid> call, Response<CheckAccountValid> response) {
                        boolean isValid = response.body().isAccountValid();
                        Log.d(TAG, "onResponse: isValid: " + isValid);
                        if (isValid){
                            dataManager.saveEmailId(edtAccount.getText().toString());
                            openMainActivity();
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CheckAccountValid> call, Throwable t) {
                        Log.d(TAG, "onFailure: Fail");
                        Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Log.d(TAG, "buttonFunction: heheheheh");
                httpClient.getHttpAPI().checkManagerValid(userName, password).enqueue(new Callback<CheckAccountValid>() {
                    @Override
                    public void onResponse(Call<CheckAccountValid> call, Response<CheckAccountValid> response) {
                        boolean isValid = response.body().isAccountValid();
                        Log.d(TAG, "onResponse: manager isValid: " + isValid);
                        if (isValid){
                            dataManager.saveEmailId(edtAccount.getText().toString());
                            openMainActivity();
                        } else {
                            Log.d(TAG, "onResponse: jjjjjjjjjjjj");
                            Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CheckAccountValid> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        } else {
            httpClient.getHttpAPI().signUpAccount(userName, password).enqueue(new Callback<CheckAccountValid>() {
                @Override
                public void onResponse(Call<CheckAccountValid> call, Response<CheckAccountValid> response) {
                    boolean isValid = response.body().isAccountValid();
                    Log.d(TAG, "onResponse: isValid: " + isValid);
                    if (isValid){
                        Toast.makeText(getApplicationContext(), "Signup successfully", Toast.LENGTH_SHORT).show();
                        edtAccount.setText("");
                        edtPassword.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Username existed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CheckAccountValid> call, Throwable t) {
                    Log.d(TAG, "onFailure: Fail");
                    Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
