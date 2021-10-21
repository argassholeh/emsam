package com.sholeh.emsam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ActivityDetailKaryawan extends AppCompatActivity implements View.OnClickListener {


    TextView tvx_title, tvx_logout, tvx_print, tvx_download, tvx_batal, tvx_ubah, tvx_username, tvx_pengenal;
    EditText tvx_nama, tvx_jabatan, tvx_tglMulaiTugas,
            tvx_ttl, tvx_nopengenal, tvx_status, tvx_nohp, tvx_alamat;
    ImageView img_detail, imgtoolbar;
    Button btn_cetak;
    String id_user, nama, username, jabatan, tgltugas, ttl, pengenal, nopengenal, status, nohp, alamat, foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_karyawan);
        getSupportActionBar().hide();

        imgtoolbar = findViewById(R.id.imgtoolbarFBack);
        tvx_batal = findViewById(R.id.tvBatal);
        tvx_ubah = findViewById(R.id.tvSave);
        tvx_print = findViewById(R.id.tv_print);
        tvx_download = findViewById(R.id.tv_download);
        tvx_title = findViewById(R.id.tvx_title);
        tvx_logout = findViewById(R.id.logout_akun);
        tvx_logout.setVisibility(View.GONE);
        tvx_print.setVisibility(View.VISIBLE);
        tvx_download.setVisibility(View.VISIBLE);
        tvx_title.setText("Detail Karyawan");
        tvx_title.setTypeface(tvx_title.getTypeface(), Typeface.BOLD);
        tvx_title.setTextColor(Color.parseColor("#FFFFFF"));


        tvx_nama = findViewById(R.id.tv_nama);
        tvx_username = findViewById(R.id.tv_username);
        tvx_jabatan = findViewById(R.id.tv_jabatan);
        tvx_tglMulaiTugas = findViewById(R.id.tv_tanggalmulaitugas);
        tvx_ttl = findViewById(R.id.tv_ttl);
        tvx_pengenal = findViewById(R.id.tv_pengenal);
        tvx_nopengenal = findViewById(R.id.tv_nopengenal);
        tvx_status = findViewById(R.id.tv_status);
        tvx_nohp = findViewById(R.id.tv_nohp);
        tvx_alamat = findViewById(R.id.tv_alamat);
        img_detail = findViewById(R.id.img_detailfoto);
        id_user = getIntent().getStringExtra("id_user");
        nama = getIntent().getStringExtra("nama");
        username = getIntent().getStringExtra("username");
        jabatan = getIntent().getStringExtra("jabatan");
        tgltugas = getIntent().getStringExtra("tanggaltugas");
        ttl = getIntent().getStringExtra("ttl");
        pengenal = getIntent().getStringExtra("pengenal");
        nopengenal = getIntent().getStringExtra("nopengenal");
        status = getIntent().getStringExtra("status");
        nohp = getIntent().getStringExtra("nohp");
        alamat = getIntent().getStringExtra("alamat");
        foto = getIntent().getStringExtra("foto_karyawan");

        tvx_nama.setText(nama);
        tvx_username.setText(username);
        tvx_jabatan.setText(jabatan);
        tvx_tglMulaiTugas.setText(tgltugas);
        tvx_ttl.setText(ttl);
        tvx_pengenal.setText(pengenal);
        tvx_nopengenal.setText(nopengenal);
        tvx_status.setText(status);
        tvx_nohp.setText(nohp);
        tvx_alamat.setText(alamat);
        Glide.with(ActivityDetailKaryawan.this)
                .load(foto)
                .apply(new RequestOptions().placeholder(R.mipmap.no_image).centerCrop())
                .into(img_detail);


        tvx_ubah.setOnClickListener(this);
        tvx_batal.setOnClickListener(this);
        tvx_print.setOnClickListener(this);
        tvx_download.setOnClickListener(this);
        imgtoolbar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvSave:
                Toast.makeText(this, "klik", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;

        }

    }
}