package com.ecom.buylo.Model.request;

import com.ecom.buylo.Model.response.Vendo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vendor {
    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Vendo data;

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

    public Vendo getData() {
        return data;
    }

    public void setData(Vendo data) {
        this.data = data;
    }
}
