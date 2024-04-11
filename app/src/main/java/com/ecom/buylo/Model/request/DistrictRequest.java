package com.ecom.buylo.Model.request;

import com.ecom.buylo.Model.response.District;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistrictRequest {
    @SerializedName("data")
    @Expose
    private List<District> data;

    public List<District> getData() {
        return data;
    }

    public void setData(List<District> data) {
        this.data = data;
    }
}
