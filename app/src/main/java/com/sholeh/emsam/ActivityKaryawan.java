package com.sholeh.emsam;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.developer.kalert.KAlertDialog;
import com.sholeh.emsam.Adapter.adapterspin;
import com.sholeh.emsam.Api.BaseApiService;
import com.sholeh.emsam.Api.UrlApi;
import com.sholeh.emsam.Model.ResponseJabatan;
import com.sholeh.emsam.Model.ResponseServer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityKaryawan extends AppCompatActivity implements View.OnClickListener {


    TextView tvx_title, tvx_logout;
    //    , tvx_logout, tvx_print, tvx_download, tvx_batal, tvx_ubah, tvx_username, tvx_pengenal;
    EditText edNama, edTglTugas, edtttl, edNoPengenal, edPendidikan, edKeterampilan, ednohp,
            edbpjsKesehatan, edbpjsKetenagakerjaan, edalamat;
    Spinner spinJabatan, spinKartuPengenal, spinAgama, spinStatus, spinLevel;
    ImageView img_detail, imgtoolbar;
    Button btn_cetak;
    String id_user, nama, username, jabatan, tgltugas, ttl, pengenal, nopengenal, status, nohp, alamat, foto,
            idjabatan, agama, jk, level, pendidikan, keterampilan, nobpjskesehatan, nobpjsketenaga;
    RadioButton rbL, rbP;
    BaseApiService ApiService;
    adapterspin adapterspinner;
    ArrayList<String> arrayspinJabatan = new ArrayList<>();
    ArrayList<String> listID_jabatan = new ArrayList<>();
    ArrayList<String> listKartuPengenal = new ArrayList<>();
    ArrayList<String> listAgama = new ArrayList<>();
    ArrayList<String> listStatus = new ArrayList<>();
    ArrayList<String> listLevel = new ArrayList<>();
    KAlertDialog pDialog;
    Button btnUpdate;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private SimpleDateFormat dateFormatter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_karyawan);
        getSupportActionBar().hide();
        tvx_logout = findViewById(R.id.logout_akun);
        tvx_logout.setVisibility(View.GONE);
        ApiService = UrlApi.getAPIService();

        btnUpdate = findViewById(R.id.btnSimpan);
        spinKartuPengenal = findViewById(R.id.spin_kartu);
        spinJabatan = findViewById(R.id.spin_jabatan);
        spinAgama = findViewById(R.id.spinagama);
        spinStatus = findViewById(R.id.spinstatus);
        spinLevel = findViewById(R.id.spinslevel);
        edNoPengenal = findViewById(R.id.edNomorPengenal);
        rbL = findViewById(R.id.rbL);
        rbP = findViewById(R.id.rbP);
        edNama = findViewById(R.id.edNama);
        edTglTugas = findViewById(R.id.edtglTugas);
        edtttl = findViewById(R.id.edttl);
        edPendidikan = findViewById(R.id.edPendidikan);
        edKeterampilan = findViewById(R.id.edKeterampilan);
        edbpjsKesehatan = findViewById(R.id.edbpjsKesehatan);
        edbpjsKetenagakerjaan = findViewById(R.id.edbpjsketenagaKerjaan);
        ednohp = findViewById(R.id.ednohp);
        edalamat = findViewById(R.id.edAlamat);


        imgtoolbar = findViewById(R.id.imgtoolbarFBack);
        tvx_title = findViewById(R.id.tvx_title);
        tvx_title.setText("Ubah Karyawan");
        tvx_title.setTypeface(tvx_title.getTypeface(), Typeface.BOLD);
        tvx_title.setTextColor(Color.parseColor("#FFFFFF"));


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

        edNoPengenal.setText(nopengenal);
        edNama.setText(nama);
        edTglTugas.setText(tgltugas);
        edtttl.setText(ttl);
        edPendidikan.setText(pendidikan);
        edKeterampilan.setText(keterampilan);
        edbpjsKesehatan.setText(nobpjskesehatan);
        edbpjsKetenagakerjaan.setText(nobpjsketenaga);
        ednohp.setText(nohp);
        edalamat.setText(alamat);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        getResultListJabatan();
        isiKartuPengenal();
        isiAgama();
        isilevel();
        isistatus();
        btnUpdate.setOnClickListener(this);
        edTglTugas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSimpan:
                sendData();
                break;

            case R.id.edtglTugas:
                showDateDialog();
                break;



            default:
                break;

        }

    }

    private void getResultListJabatan() {
        ApiService.getsemuaJabatan().enqueue(new Callback<ResponseJabatan>() {
            @Override
            public void onResponse(Call<ResponseJabatan> call, Response<ResponseJabatan> response) {
                if (response.isSuccessful()) {

                    int count_data = response.body().getSemuajabatan().size();
                    for (int a = 0; a <= count_data - 1; a++) {
                        arrayspinJabatan.add(response.body().getSemuajabatan().get(a).getJabatan());
                        listID_jabatan.add(response.body().getSemuajabatan().get(a).getIdJabatan());

                    }
                    adapterspinner = new adapterspin(ActivityKaryawan.this, R.layout.support_simple_spinner_dropdown_item);
                    adapterspinner.addAll(arrayspinJabatan);
                    adapterspinner.add("Pilih Jabatan");
                    spinJabatan.setAdapter(adapterspinner);
                    spinJabatan.setSelection(adapterspinner.getCount());
                } else {
                    String error = "Error Retrive DataProfil from Server !!!";
                    Toast.makeText(ActivityKaryawan.this, error, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJabatan> call, Throwable t) {

            }
        });
    }

    private void isiKartuPengenal() {
        listKartuPengenal.add("Pilih Kartu Pengenal");
        listKartuPengenal.add("KTP");
        listKartuPengenal.add("SIM");
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listKartuPengenal);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinKartuPengenal.setAdapter(adapterStatus);
    }

    private void isiAgama() {
        listAgama.add("Pilih Agama");
        listAgama.add("Islam");
        listAgama.add("Kristen");
        listAgama.add("Budha");
        listAgama.add("Hindu");
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAgama);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAgama.setAdapter(adapterStatus);
    }

    private void isistatus() {
        listStatus.add("Pilih Status");
        listStatus.add("PKWT");
        listStatus.add("PKWTT");
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listStatus);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinStatus.setAdapter(adapterStatus);
    }

    private void isilevel() {
        listLevel.add("Pilih Level");
        listLevel.add("user");
        listLevel.add("admin");
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listLevel);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinLevel.setAdapter(adapterStatus);
    }

    private void sendData() {
        nama = edNama.getText().toString();
        jabatan = spinJabatan.getSelectedItem().toString();
        tgltugas = edTglTugas.getText().toString();
        ttl = edtttl.getText().toString();
        nopengenal = edNoPengenal.getText().toString();
         pendidikan = edPendidikan.getText().toString();
        keterampilan = edKeterampilan.getText().toString();
        nobpjskesehatan = edbpjsKesehatan.getText().toString();
        nobpjsketenaga = edbpjsKetenagakerjaan.getText().toString();
        nohp = ednohp.getText().toString();
        alamat = edalamat.getText().toString();

        spinJabatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinJabatan.getSelectedItem().equals("Pillih Jabatan")) {

                } else {
//                    getCity(listID_prov.get(position));
//                    namaProvinsi = spinProvinsi.getSelectedItem().toString();
//                    Toast.makeText(AddWargaActivity.this, "id: " + listID_kelurahan.get(position) + " kelurahan: " + spinKelurahan.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (nama.isEmpty()) {
            pDialog = new KAlertDialog(ActivityKaryawan.this, KAlertDialog.ERROR_TYPE);
            pDialog.setTitleText("Oops...");
            pDialog.setContentText("Harap masukkan data dengan benar! ");
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
            requestUpdate();
        }
    }


    private void requestUpdate() {
        pDialog = new KAlertDialog(this, KAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        agama = spinAgama.getSelectedItem().toString();
        status = spinStatus.getSelectedItem().toString();
        level = spinLevel.getSelectedItem().toString();
        pengenal = spinKartuPengenal.getSelectedItem().toString();
        if (rbL.isChecked()) {
            jk = "L";
        }else{
            jk = "P";
        }
        final String idjabatan_ = listID_jabatan.get(spinJabatan.getSelectedItemPosition());
        ApiService.ubahKaryawan(id_user, nama, idjabatan_, tgltugas, ttl, pengenal, nopengenal, agama, jk, status,level, pendidikan,
                keterampilan, nobpjskesehatan, edbpjsKetenagakerjaan.getText().toString(), nohp, alamat
        ).enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                pDialog.dismissWithAnimation();
                if (response.body().getMessage().equalsIgnoreCase("Berhasil di ubah")) {
                    pDialog.dismissWithAnimation();
                    Toast.makeText(ActivityKaryawan.this, "Berhasil di ubah", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(ActvityKaryawan.this, FragmentMainRegister.class);
//                    intent.putExtra("idnik", nik);
//                    startActivity(intent);
                    finish();


                } else {
                    pDialog.dismissWithAnimation();
                    pDialog.dismissWithAnimation();
                    pDialog = new KAlertDialog(ActivityKaryawan.this, KAlertDialog.ERROR_TYPE);
                    pDialog.setTitleText("Oops...");
                    pDialog.setContentText("Terjadi kesalahan dalam proses penambahan anggota, silahkan coba beberapa saat lagi.");
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
            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                pDialog.dismissWithAnimation();
                pDialog = new KAlertDialog(ActivityKaryawan.this, KAlertDialog.ERROR_TYPE);
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


    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edTglTugas.setText(dateFormatter.format(newDate.getTime()));
//                etTempat.requestFocus();
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void showTimeDialog() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(ActivityKaryawan.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                etJam.setText(selectedHour + ":" + selectedMinute);
//                etTempat.requestFocus();
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }


}