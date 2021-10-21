package com.sholeh.emsam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.developer.kalert.KAlertDialog;
import com.sholeh.emsam.Api.BaseApiService;
import com.sholeh.emsam.Api.UrlApi;
import com.sholeh.emsam.Helper.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    EditText edt_username, edt_password;
    Button btn_login, btn_bersihkan;

    KAlertDialog pDialog;
    Context context;
    BaseApiService apiService;

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        edt_username = findViewById(R.id.etUsername);
        edt_password = findViewById(R.id.etPassword);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        context = this;
        apiService = UrlApi.getAPIService();
        preferences = new Preferences(this);
        if (preferences.getSPSudahLogin()) {
            this.finish();
            startActivity(new Intent(ActivityLogin.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;


            default:
                break;
        }
    }

    private void login(){
        if (edt_username.getText().toString().isEmpty()) {
            pDialog =   new KAlertDialog(this, KAlertDialog.ERROR_TYPE);
            pDialog.setTitleText("Oops...");
            pDialog.setContentText("Username harus diisi!");
            pDialog.show();
            Thread timer = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1500);
                        pDialog.cancel();
                        super.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            timer.start();


        }else if(edt_password.getText().toString().isEmpty() ){
            pDialog = new KAlertDialog(this, KAlertDialog.ERROR_TYPE);
            pDialog.setTitleText("Oops...");
            pDialog.setContentText("Password harus diisi!");
            pDialog.show();
            Thread timer = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1500);
                        pDialog.cancel();
                        super.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            timer.start();
        } else {
            pDialog = new KAlertDialog(this, KAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Sedang memproses login..");
            pDialog.setCancelable(false);
            pDialog.show();

            requestLogin();
        }
    }

    private void requestLogin(){
        apiService.loginRequest(edt_username.getText().toString(), edt_password.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                pDialog.dismissWithAnimation();

                if (response.isSuccessful()){
                    try {
                        JSONObject result = new JSONObject(response.body().string());
                        if (result.getString("error").equals("false")) {
                            String idPengguna   = result.getString("key");
                            String username = result.getJSONObject("user").getString("username");
                            String level = result.getJSONObject("user").getString("level");

                            preferences.saveSPBoolean(Preferences.SP_SUDAH_LOGIN, true);
                            preferences.saveSPString(Preferences.SP_IdPengguna, idPengguna);
                            preferences.saveSPString(preferences.SP_UserName, username);
                            preferences.saveSPString(preferences.SP_STATUS, level);

                            openUtama(idPengguna, level);
                        }
                        else{
                            pDialog = new KAlertDialog(ActivityLogin.this, KAlertDialog.ERROR_TYPE);
                            pDialog.setTitleText("Oops...");
                            pDialog.setContentText("Username atau password anda salah!");
                            pDialog.show();
                            Thread timer = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        sleep(1500);
                                        pDialog.cancel();
                                        super.run();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                }
                            };
                            timer.start();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                pDialog.dismissWithAnimation();
                pDialog = new KAlertDialog(ActivityLogin.this, KAlertDialog.ERROR_TYPE);
                pDialog.setTitleText("Oops...");
                pDialog.setContentText("Koneksi internet bermasalah!");
                pDialog.show();
                Thread timer = new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(1500);
                            pDialog.cancel();
                            super.run();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
                timer.start();
            }
        });
    }

    private void openUtama(String idPengguna, String status) {
//        if (preferences.getSPStatus().equalsIgnoreCase("admin")) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(preferences.SP_IdPengguna, idPengguna);
        intent.putExtra(preferences.SP_STATUS, status);
        startActivity(intent);
        finish();
//        }
    }


}