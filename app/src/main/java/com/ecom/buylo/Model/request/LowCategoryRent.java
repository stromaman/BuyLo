package com.ecom.buylo.Model.request;

import com.ecom.buylo.Model.response.LowCategory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LowCategoryRent {
    @SerializedName("data")
    @Expose
    private List<LowCategory> data;
    @SerializedName("Type")
    @Expose
    private String type;

    public List<LowCategory> getData() {
        return data;
    }

    public void setData(List<LowCategory> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
