package com.ecom.buylo.Model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorProduct {
    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
