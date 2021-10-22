package com.sholeh.emsam;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sholeh.emsam.Adapter.adapterspin;
import com.sholeh.emsam.Api.BaseApiService;
import com.sholeh.emsam.Api.UrlApi;
import com.sholeh.emsam.Model.ResponseJabatan;

import java.util.ArrayList;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_karyawan);
        getSupportActionBar().hide();
        tvx_logout = findViewById(R.id.logout_akun);
        tvx_logout.setVisibility(View.GONE);
        ApiService = UrlApi.getAPIService();


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
        getResultListJabatan();
        isiKartuPengenal();
        isiAgama();
        isilevel();
        isistatus();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
//            case R.id.tvSave:
//                Toast.makeText(this, "klik", Toast.LENGTH_SHORT).show();
//                break;

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


}