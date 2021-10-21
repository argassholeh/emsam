package com.sholeh.emsam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.developer.kalert.KAlertDialog;
import com.sholeh.emsam.Api.BaseApiService;
import com.sholeh.emsam.Api.UrlApi;
import com.sholeh.emsam.Helper.Preferences;

public class MainActivity extends AppCompatActivity {

    Preferences preferences;
    KAlertDialog pDialog;
    BaseApiService ApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = new Preferences(this);
        ApiService = UrlApi.getAPIService();

        cekUser();
    }

    private void cekUser(){
        if(preferences.getSPStatus().equalsIgnoreCase("admin")){
            Toast.makeText(this, "admin", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "user", Toast.LENGTH_SHORT).show();

        }
    }
}