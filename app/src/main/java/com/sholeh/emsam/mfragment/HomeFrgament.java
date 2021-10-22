/*
 * Copyright (c) Muhammad Solehudin
 */

package com.sholeh.emsam.mfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.kalert.KAlertDialog;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sholeh.emsam.ActivityLogin;
import com.sholeh.emsam.Adapter.AdapterKaryawan;
import com.sholeh.emsam.Api.BaseApiService;
import com.sholeh.emsam.Api.UrlApi;
import com.sholeh.emsam.Helper.Preferences;
import com.sholeh.emsam.Model.ItemKaryawan;
import com.sholeh.emsam.Model.ResponseKaryawan;
import com.sholeh.emsam.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFrgament extends Fragment implements View.OnClickListener {

    TextView tvx_title, tvx_logout;
    ImageView imgtoolbar;
    private List<ItemKaryawan> listDataKaryawan = new ArrayList<>();
    ;
    private AdapterKaryawan adapter;
    BaseApiService ApiService;
    RecyclerView rv_karyawan;
    KAlertDialog pDialog;
    private KProgressHUD progressDialogHud;
    Preferences preferences;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        imgtoolbar = rootView.findViewById(R.id.imgtoolbarFBack);
        imgtoolbar.setVisibility(View.GONE);
        tvx_title = rootView.findViewById(R.id.tvx_title);
        tvx_logout = rootView.findViewById(R.id.logout_akun);
        rv_karyawan = rootView.findViewById(R.id.recycler_karyawan);
        progressDialogHud = KProgressHUD.create(getActivity());
        preferences = new Preferences(getActivity());


        tvx_title.setText("Data Karyawan");
        tvx_title.setTypeface(tvx_title.getTypeface(), Typeface.BOLD);
        tvx_title.setTextColor(Color.parseColor("#FFFFFF"));
//        tvx_logout.setVisibility(View.GONE);

        ApiService = UrlApi.getAPIService();
        adapter = new AdapterKaryawan(getActivity(), listDataKaryawan);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getActivity());
        rv_karyawan.setLayoutManager(LayoutManager);
        rv_karyawan.setHasFixedSize(true);
        getResultList();
        tvx_logout.setOnClickListener(this);
        return rootView;
    }

    public void getResultList() {
        pDialog = new KAlertDialog(getActivity(), KAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Memuat Data");
        pDialog.setCancelable(false);
        pDialog.show();

//        if(level.equals("warek")){
        ApiService.getKaryawan().enqueue(new Callback<ResponseKaryawan>() {
            @Override
            public void onResponse(Call<ResponseKaryawan> call, Response<ResponseKaryawan> response) {
                if (response.isSuccessful()) {
                    pDialog.dismissWithAnimation();
                    final List<ItemKaryawan> ListItem = response.body().getSemuakaryawan();
                    rv_karyawan.setAdapter(new AdapterKaryawan(getActivity(), ListItem));
                    adapter.notifyDataSetChanged();

//                        initDataIntent(ListItem);
                } else {
                    pDialog.dismissWithAnimation();
                    new KAlertDialog(getActivity(), KAlertDialog.ERROR_TYPE)
                            .setTitleText("Kesalahan")
                            .setContentText("Gagal mendapatkan data!")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKaryawan> call, Throwable t) {
                pDialog.dismissWithAnimation();
                new KAlertDialog(getActivity(), KAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Koneksi internet bermasalah!")
                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                            @Override
                            public void onClick(KAlertDialog sDialog) {
                                getActivity().finish();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_akun:
                logoutAkun();
                break;

            default:
                break;
        }
    }

    public void logoutAkun() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Apakah anda yakin, ingin logout?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProgresDialog();
                preferences.saveSPBoolean(preferences.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(getActivity(), ActivityLogin.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
                Toast.makeText(getActivity(), "Berhasil Keluar", Toast.LENGTH_LONG).show();
                progressDialogHud.dismiss();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void ProgresDialog() {
        progressDialogHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
        progressDialogHud.show();
    }


}

