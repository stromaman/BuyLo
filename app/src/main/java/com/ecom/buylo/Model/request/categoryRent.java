package com.ecom.buylo.Model.request;

import com.ecom.buylo.Model.response.Rent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class categoryRent {
    @SerializedName("data")
    @Expose
    private List<Rent> data;
    @SerializedName("Type")
    @Expose
    private String type;

    public List<Rent> getData() {
        return data;
    }

    public void setData(List<Rent> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
