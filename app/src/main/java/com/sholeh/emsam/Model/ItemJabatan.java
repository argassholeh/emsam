package com.sholeh.emsam.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemJabatan {
    @SerializedName("id_jabatan")
    @Expose
    private String idJabatan;
    @SerializedName("jabatan")
    @Expose
    private String jabatan;

    public String getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(String idJabatan) {
        this.idJabatan = idJabatan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

}
