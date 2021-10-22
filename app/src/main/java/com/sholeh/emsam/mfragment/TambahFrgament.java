/*
 * Copyright (c) Muhammad Solehudin
 */

package com.sholeh.emsam.mfragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sholeh.emsam.ActivityKaryawan;
import com.sholeh.emsam.Adapter.adapterspin;
import com.sholeh.emsam.Api.BaseApiService;
import com.sholeh.emsam.Api.UrlApi;
import com.sholeh.emsam.Model.ResponseJabatan;
import com.sholeh.emsam.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TambahFrgament extends Fragment {


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tambah,container,false);
//        getSupportActionBar().hide();
        tvx_logout = rootView.findViewById(R.id.logout_akun);
        tvx_logout.setVisibility(View.GONE);
        ApiService = UrlApi.getAPIService();


        spinKartuPengenal = rootView.findViewById(R.id.spin_kartu);
        spinJabatan = rootView.findViewById(R.id.spin_jabatan);
        spinAgama = rootView.findViewById(R.id.spinagama);
        spinStatus = rootView.findViewById(R.id.spinstatus);
        spinLevel = rootView.findViewById(R.id.spinslevel);
        edNoPengenal = rootView.findViewById(R.id.edNomorPengenal);
        rbL = rootView.findViewById(R.id.rbL);
        rbP = rootView.findViewById(R.id.rbP);
        edNama = rootView.findViewById(R.id.edNama);
        edTglTugas = rootView.findViewById(R.id.edtglTugas);
        edtttl = rootView.findViewById(R.id.edttl);
        edPendidikan = rootView.findViewById(R.id.edPendidikan);
        edKeterampilan = rootView.findViewById(R.id.edKeterampilan);
        edbpjsKesehatan = rootView.findViewById(R.id.edbpjsKesehatan);
        edbpjsKetenagakerjaan = rootView.findViewById(R.id.edbpjsketenagaKerjaan);
        ednohp = rootView.findViewById(R.id.ednohp);
        edalamat = rootView.findViewById(R.id.edAlamat);


        imgtoolbar = rootView.findViewById(R.id.imgtoolbarFBack);
        tvx_title = rootView.findViewById(R.id.tvx_title);
        tvx_title.setText("Tambah Karyawan");
        tvx_title.setTypeface(tvx_title.getTypeface(), Typeface.BOLD);
        tvx_title.setTextColor(Color.parseColor("#FFFFFF"));


        getResultListJabatan();
        isiKartuPengenal();
        isiAgama();
        isilevel();
        isistatus();

        return rootView;
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
                    adapterspinner = new adapterspin(getActivity(), R.layout.support_simple_spinner_dropdown_item);
                    adapterspinner.addAll(arrayspinJabatan);
                    adapterspinner.add("Pilih Jabatan");
                    spinJabatan.setAdapter(adapterspinner);
                    spinJabatan.setSelection(adapterspinner.getCount());
                } else {
                    String error = "Error Retrive DataProfil from Server !!!";
                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
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
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listKartuPengenal);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinKartuPengenal.setAdapter(adapterStatus);
    }

    private void isiAgama() {
        listAgama.add("Pilih Agama");
        listAgama.add("Islam");
        listAgama.add("Kristen");
        listAgama.add("Budha");
        listAgama.add("Hindu");
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listAgama);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAgama.setAdapter(adapterStatus);
    }

    private void isistatus() {
        listStatus.add("Pilih Status");
        listStatus.add("PKWT");
        listStatus.add("PKWTT");
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listStatus);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinStatus.setAdapter(adapterStatus);
    }

    private void isilevel() {
        listLevel.add("Pilih Level");
        listLevel.add("user");
        listLevel.add("admin");
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listLevel);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinLevel.setAdapter(adapterStatus);
    }

}

