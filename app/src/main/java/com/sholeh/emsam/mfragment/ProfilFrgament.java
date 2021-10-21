/*
 * Copyright (c) Muhammad Solehudin
 */

package com.sholeh.emsam.mfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.sholeh.emsam.ActivityLogin;
import com.sholeh.emsam.Helper.Preferences;
import com.sholeh.emsam.R;


public class ProfilFrgament extends Fragment {
    private KProgressHUD progressDialogHud;
    Preferences preferences;
    ImageView imgtoolbar;
    TextView tvxTitle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil,container,false);
        progressDialogHud = KProgressHUD.create(getActivity());
        preferences = new Preferences(getActivity());

        return rootView;
    }

    private void ProgresDialog() {
        progressDialogHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
        progressDialogHud.show();
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


}

