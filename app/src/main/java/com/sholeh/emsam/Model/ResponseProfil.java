package com.sholeh.emsam.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseProfil {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("profilkaryawan")
    @Expose
    private List<ItemProfil> profilkaryawan = null;

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

    public List<ItemProfil> getProfilkaryawan() {
        return profilkaryawan;
    }

    public void setProfilkaryawan(List<ItemProfil> profilkaryawan) {
        this.profilkaryawan = profilkaryawan;
    }
}
