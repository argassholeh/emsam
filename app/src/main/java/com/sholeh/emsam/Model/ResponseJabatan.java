package com.sholeh.emsam.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseJabatan {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("semuajabatan")
    @Expose
    private List<ItemJabatan> semuajabatan = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ItemJabatan> getSemuajabatan() {
        return semuajabatan;
    }

    public void setSemuajabatan(List<ItemJabatan> semuajabatan) {
        this.semuajabatan = semuajabatan;
    }


}
