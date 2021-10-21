package com.sholeh.emsam;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.kalert.KAlertDialog;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sholeh.emsam.Api.BaseApiService;
import com.sholeh.emsam.Api.UrlApi;
import com.sholeh.emsam.Helper.Preferences;
import com.sholeh.emsam.databinding.ActivityUtamaBinding;
import com.sholeh.emsam.mfragment.HomeFrgament;
import com.sholeh.emsam.mfragment.ProfilFrgament;
import com.sholeh.emsam.mfragment.TambahFrgament;

public class ActivityUtama extends AppCompatActivity {

    private ActivityUtamaBinding binding;
    private BottomAppBar bottomAppBar;
    LinearLayout ln_home, ln_profil;
    ImageView imgNavHome, imgNavProfil;
    TextView tvxHome, tvxProfil;
    Context context;
    FloatingActionButton fab_add;
    ContextCompat contextCompat;
    Preferences preferences;
    KAlertDialog pDialog;
    BaseApiService ApiService;
    boolean aksesTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        preferences = new Preferences(this);
        ApiService = UrlApi.getAPIService();

        fab_add = findViewById(R.id.fab_add);
        tvxProfil = findViewById(R.id.tv_profil);
        tvxHome = findViewById(R.id.tvhome);
        imgNavHome = findViewById(R.id.img_navhome);
        imgNavProfil = findViewById(R.id.img_navprofil);
        bottomAppBar = findViewById(R.id.navigation);
        ln_home = findViewById(R.id.nav_home);
        ln_profil = findViewById(R.id.nav_profile);
        ln_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_add.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                tvxProfil.setTextColor(Color.parseColor("#FFFFFF"));
                imgNavProfil.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.white)));
                tvxHome.setTextColor(Color.parseColor("#81FFFFFF"));
                imgNavHome.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite)));
                HomeFrgament homeFrgament = new HomeFrgament();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main_id, homeFrgament).commit();
                fab_add.setImageDrawable(getResources().getDrawable(R.drawable.ic_add));

            }
        });
        ln_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_add.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                tvxProfil.setTextColor(Color.parseColor("#81FFFFFF"));
                imgNavProfil.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite)));
                tvxHome.setTextColor(Color.parseColor("#FFFFFF"));
                imgNavHome.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.white)));
                ProfilFrgament inboxFragment = new ProfilFrgament();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main_id, inboxFragment).commit();
                fab_add.setImageDrawable(getResources().getDrawable(R.drawable.ic_add));

            }
        });

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_add.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDarkk)));
                tvxProfil.setTextColor(Color.parseColor("#FFFFFF"));
                imgNavProfil.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.white)));
                tvxHome.setTextColor(Color.parseColor("#FFFFFF"));
                imgNavHome.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.white)));
                fab_add.setImageDrawable(getResources().getDrawable(R.drawable.ic_add2));
//                fab_add.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_home));
               cekAksesFab();
            }
        });


        cekUser();
    }


    private void cekUser(){
        if(preferences.getSPStatus().equalsIgnoreCase("admin")){
            Toast.makeText(this, "admin", Toast.LENGTH_SHORT).show();
            aksesTambah = true;
            HomeFrgament homeFrgament = new HomeFrgament();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main_id, homeFrgament).commit();
            fab_add.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
            tvxProfil.setTextColor(Color.parseColor("#FFFFFF"));
            imgNavProfil.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.white)));
            tvxHome.setTextColor(Color.parseColor("#81FFFFFF"));
            imgNavHome.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite)));

        }else {
            Toast.makeText(this, "user", Toast.LENGTH_SHORT).show();
            fab_add.setVisibility(View.VISIBLE);
            ln_home.setVisibility(View.GONE);
            ln_profil.setVisibility(View.GONE);
            fab_add.setImageDrawable(getResources().getDrawable(R.drawable.ic_profil));
            aksesTambah = false;
            ProfilFrgament inboxFragment = new ProfilFrgament();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main_id, inboxFragment).commit();
        }
    }

    private void cekAksesFab() {
        if (aksesTambah){
            TambahFrgament tambahFrgament = new TambahFrgament();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main_id, tambahFrgament).commit();

        }else{
            ProfilFrgament inboxFragment = new ProfilFrgament();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main_id, inboxFragment).commit();

        }
    }


}