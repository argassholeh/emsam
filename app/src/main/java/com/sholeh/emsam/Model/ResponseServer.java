package com.sholeh.emsam.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseServer {

    @SerializedName("error")
    boolean error;

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public boolean getError() {
        return error;
    }
}
