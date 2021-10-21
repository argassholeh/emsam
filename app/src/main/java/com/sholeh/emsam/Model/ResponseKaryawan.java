package com.sholeh.emsam.Model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseKaryawan {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("semuakaryawan")
    @Expose
    private List<ItemKaryawan> semuakaryawan = null;

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

    public List<ItemKaryawan> getSemuakaryawan() {
        return semuakaryawan;
    }

    public void setSemuakaryawan(List<ItemKaryawan> semuakaryawan) {
        this.semuakaryawan = semuakaryawan;
    }

}
