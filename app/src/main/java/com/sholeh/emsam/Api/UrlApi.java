package com.sholeh.emsam.Api;

public class UrlApi {

    public static final String BASE_URL_API = "http://192.168.43.60/apikkub/";

    public static final String BASE_URL_HOSTING = "https://emsam.monitoringpltmh.com/";
//    public static final String BASE_URL = "http://192.168.43.60/";
//    public static final String BASE_URL = "https://emsam.monitoringpltmh.com/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_HOSTING).create(BaseApiService.class);
    }
}
