package com.ecom.buylo.Model.request;

import com.ecom.buylo.Model.response.City;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityRequest {
    @SerializedName("data")
    @Expose
    private List<City> data;

    public List<City> getData() {
        return data;
    }

    public void setData(List<City> data) {
        this.data = data;
    }
}
