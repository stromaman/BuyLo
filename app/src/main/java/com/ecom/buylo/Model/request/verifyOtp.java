package com.ecom.buylo.Model.request;

import com.ecom.buylo.Model.response.OtpVerify;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class verifyOtp {
    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private OtpVerify data;

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

    public OtpVerify getData() {
        return data;
    }

    public void setData(OtpVerify data) {
        this.data = data;
    }

}
