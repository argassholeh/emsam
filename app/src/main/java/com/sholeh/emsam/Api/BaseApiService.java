package com.sholeh.emsam.Api;


import com.sholeh.emsam.Model.ResponseJabatan;
import com.sholeh.emsam.Model.ResponseKaryawan;
import com.sholeh.emsam.Model.ResponseServer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    @GET("tampil-karyawan.php")
    Call<ResponseKaryawan> getKaryawan();

    @GET("tampil-spinjabatan.php")
    Call<ResponseJabatan> getsemuaJabatan();

    @FormUrlEncoded
    @POST("ubah-karyawan.php")
    Call<ResponseServer> ubahKaryawan(@Field("id_user") String idUser,
                                      @Field("nama_pekerja") String namaPekerja,
                                      @Field("id_jabatan") String idJabatan,
                                      @Field("tgl_mulaitugas") String tglMulaiTugas,
                                      @Field("ttl") String ttl,
                                      @Field("kartu_pengenal") String kartuPengenal,
                                      @Field("nomor_pengenal") String nomorPengenal,
                                      @Field("agama") String agama,
                                      @Field("jenis_kelamin") String jk,
                                      @Field("status") String status,
                                      @Field("level") String level,
                                      @Field("pendidikan") String pendidikan,
                                      @Field("keterampilan") String keterampilan,
                                      @Field("no_bpjskesehatan") String nobpjsKesehatan,
                                      @Field("no_bpjsketenagakerjaan") String nobpjsKetenaga,
                                      @Field("no_hp") String nohp,
                                      @Field("alamat") String alamaat);

    @FormUrlEncoded
    @POST("simpan-karyawan.php")
    Call<ResponseServer> simpanKaryawan(@Field("nama_pekerja") String namaPekerja,
                                      @Field("id_jabatan") String idJabatan,
                                      @Field("tgl_mulaitugas") String tglMulaiTugas,
                                      @Field("ttl") String ttl,
                                      @Field("kartu_pengenal") String kartuPengenal,
                                      @Field("nomor_pengenal") String nomorPengenal,
                                      @Field("agama") String agama,
                                      @Field("jenis_kelamin") String jk,
                                      @Field("status") String status,
                                      @Field("level") String level,
                                      @Field("pendidikan") String pendidikan,
                                      @Field("keterampilan") String keterampilan,
                                      @Field("no_bpjskesehatan") String nobpjsKesehatan,
                                      @Field("no_bpjsketenagakerjaan") String nobpjsKetenaga,
                                      @Field("no_hp") String nohp,
                                      @Field("alamat") String alamaat);

}
