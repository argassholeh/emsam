package com.sholeh.emsam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.developer.kalert.KAlertDialog;
import com.sholeh.emsam.Api.BaseApiService;
import com.sholeh.emsam.Helper.Preferences;

public class ActivityLogin extends AppCompatActivity {

    EditText edt_username, edt_password;
    Button btn_login, btn_bersihkan;

    KAlertDialog pDialog;
    Context context;
    BaseApiService apiService;

    boolean BackDouble = false;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}