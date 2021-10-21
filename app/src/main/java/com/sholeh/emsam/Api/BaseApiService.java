package com.sholeh.emsam.Api;


import com.sholeh.emsam.Model.ResponseKaryawan;

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
}
