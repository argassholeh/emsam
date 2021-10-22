package com.sholeh.emsam.cetakpdf.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.sholeh.emsam.R;

public class CetakPdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak_pdf);

        AndroidNetworking.initialize(getApplicationContext());


    }
}