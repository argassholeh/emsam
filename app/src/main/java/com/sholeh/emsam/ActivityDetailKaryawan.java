package com.sholeh.emsam;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.developer.kalert.KAlertDialog;
import com.sholeh.emsam.Api.BaseApiService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetailKaryawan extends AppCompatActivity implements View.OnClickListener {


    TextView tvx_title, tvx_logout, tvx_print, tvx_download, tvx_batal, tvx_ubah, tvx_username, tvx_pengenal;
    EditText tvx_nama, tvx_jabatan, tvx_tglMulaiTugas,
            tvx_ttl, tvx_nopengenal, tvx_status, tvx_nohp, tvx_alamat, etPoster;
    ImageView img_detail, imgchange, imgtoolbar;
    Button btn_cetak;
    String id_user, idfoto,  nama, username, jabatan, tgltugas, ttl, pengenal, nopengenal, status, nohp, alamat, foto,
    idjabatan, agama, jk, level, pendidikan, keterampilan, nobpjskesehatan, nobpjsketenaga;
    private static final int IMAGE = 100;
    Bitmap bitmap;
    KAlertDialog pDialog;
    BaseApiService ApiService;
    private final int PICK_IMAGE = 12345;
    private final int TAKE_PICTURE = 6352;
    private static final int REQUEST_CAMERA_ACCESS_PERMISSION = 5674;
    private Bitmap bitmap1, bitmap2, bitmap3, bitmap4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_karyawan);
        getSupportActionBar().hide();

        etPoster = findViewById(R.id.etPoster);
        imgtoolbar = findViewById(R.id.imgtoolbarFBack);
        imgchange = findViewById(R.id.imgchange);
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
        idjabatan = getIntent().getStringExtra("id_jabatan");
        agama = getIntent().getStringExtra("agama");
        jk = getIntent().getStringExtra("jk");
        level = getIntent().getStringExtra("level");
        pendidikan = getIntent().getStringExtra("pendidikan");
        keterampilan = getIntent().getStringExtra("keterampilan");
        nobpjskesehatan = getIntent().getStringExtra("nobpjs_kesehatan");
        nobpjsketenaga = getIntent().getStringExtra("nobpjs_ketenaga");
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
        imgchange.setOnClickListener(this);
        img_detail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvSave:
                Intent intent = new Intent(this, ActivityKaryawan.class);
                intent.putExtra("intent","ubah");
                intent.putExtra("id_user",id_user);
                intent.putExtra("nama",nama);
                intent.putExtra("username",username);
                intent.putExtra("id_jabatan",idjabatan);
                intent.putExtra("jabatan","jabatan");
                intent.putExtra("tanggaltugas",tgltugas);
                intent.putExtra("ttl",ttl);
                intent.putExtra("pengenal",pengenal);
                intent.putExtra("nopengenal",nopengenal);
                intent.putExtra("agama",agama);
                intent.putExtra("jk",jk);
                intent.putExtra("level",level);
                intent.putExtra("pendidikan",pendidikan);
                intent.putExtra("keterampilan",keterampilan);
                intent.putExtra("nobpjs_kesehatan",nobpjskesehatan);
                intent.putExtra("nobpjs_ketenaga",nobpjsketenaga);
                intent.putExtra("status",status);
                intent.putExtra("alamat",alamat);
                intent.putExtra("nohp",nohp);
                intent.putExtra("foto_karyawan",foto);

                startActivity(intent);
                finish();
                break;

            case R.id.imgchange:
                kegiatanFoto();
                break;

            case R.id.img_detailfoto:
                kegiatanFoto();
                break;
            default:
                break;

        }


    }

//    private void kegiatanFoto() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, IMAGE);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == IMAGE && resultCode == RESULT_OK && data != null) {
//            Uri path = data.getData();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
//                img_detail.setImageBitmap(bitmap);
//                etPoster.setText("yes");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }


//
//    private String convertToString() {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        byte[] imgByte = byteArrayOutputStream.toByteArray();
//        return Base64.encodeToString(imgByte, Base64.DEFAULT);
//    }

    private void requestSimpan() {
        pDialog = new KAlertDialog(this, KAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        String mPoster = convertToString();
        ApiService.simpanFotoBaru(id_user, mPoster).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    pDialog.dismissWithAnimation();

                    pDialog.dismissWithAnimation();
                    pDialog.dismissWithAnimation();
                    pDialog = new KAlertDialog(ActivityDetailKaryawan.this, KAlertDialog.SUCCESS_TYPE);
                    pDialog.setTitleText("Berhasil...");
                    pDialog.setContentText("Foto berhasil terupload");
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
                    pDialog.dismissWithAnimation();
                    new KAlertDialog(ActivityDetailKaryawan.this, KAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Terjadi kesalahan dalam proses pengajuan, silahkan coba beberapa saat lagi.")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                pDialog.dismissWithAnimation();
                pDialog.dismissWithAnimation();
                pDialog = new KAlertDialog(ActivityDetailKaryawan.this, KAlertDialog.ERROR_TYPE);
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


    private void tampilkameradialog() {

        final Dialog main_dialog;

        main_dialog = new Dialog(ActivityDetailKaryawan.this);
        main_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        main_dialog.setContentView(R.layout.camera_dialog);

        final Button camera = main_dialog.findViewById(R.id.camera);
        final Button folder = main_dialog.findViewById(R.id.folder);
        final ImageView close = main_dialog.findViewById(R.id.close);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ActivityCompat.checkSelfPermission(ActivityDetailKaryawan.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA_ACCESS_PERMISSION);
                } else {
                    gambardarikamera();
                }

                main_dialog.dismiss();
            }
        });


        folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihgalery();
                main_dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_dialog.dismiss();
            }
        });


        main_dialog.setCancelable(false);
        main_dialog.setCanceledOnTouchOutside(false);
        main_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        main_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        main_dialog.show();
        Window window = main_dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    private void pilihgalery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        }
    }

    private void gambardarikamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, TAKE_PICTURE);
        }

    }

    private File persistImage(Bitmap bitmap, String name) {
        File filesDir = getApplicationContext().getFilesDir();
        if (name == null) {
            Toast.makeText(ActivityDetailKaryawan.this, "Silahkan Pilih Foto Terlebih Dahulu", Toast.LENGTH_SHORT).show();
        }
        File imageFile = new File(filesDir, name + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
        }

        return imageFile;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
//                    bitmap = BitmapFactory.decodeStream(inputStream);

                    if (idfoto == "1") {
                        bitmap1 = BitmapFactory.decodeStream(inputStream);
                        foto.setImageBitmap(bitmap1);
//                    } else if (idfoto == "2") {
//                        bitmap2 = BitmapFactory.decodeStream(inputStream);
//                        ijazah.setImageBitmap(bitmap2);
//                    } else if (idfoto == "3") {
//                        bitmap3 = BitmapFactory.decodeStream(inputStream);
//                        kk.setImageBitmap(bitmap3);
//                    } else if (idfoto == "4") {
//                        bitmap4 = BitmapFactory.decodeStream(inputStream);
//                        ktp.setImageBitmap(bitmap4);
                    } else {
                        Toast.makeText(this, "Pilih Foto", Toast.LENGTH_SHORT).show();
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        } else if (requestCode == TAKE_PICTURE) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                if (idfoto == "1") {
                    bitmap1 = (Bitmap) extras.get("data");
                    foto.setImageBitmap(bitmap1);
                }
//                else if (idfoto == "2") {
//                    bitmap2 = (Bitmap) extras.get("data");
//                    ijazah.setImageBitmap(bitmap2);
//                } else if (idfoto == "3") {
//                    bitmap3 = (Bitmap) extras.get("data");
//                    kk.setImageBitmap(bitmap3);
//                } else if (idfoto == "4") {
//                    bitmap4 = (Bitmap) extras.get("data");
//                    ktp.setImageBitmap(bitmap4);
//                }
//                bitmap = (Bitmap) extras.get("data");

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_ACCESS_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gambardarikamera();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }



}