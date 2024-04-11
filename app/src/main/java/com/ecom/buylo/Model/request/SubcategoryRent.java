package com.ecom.buylo.Model.request;

import com.ecom.buylo.Model.response.Subcat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubcategoryRent {
    @SerializedName("data")
    @Expose
    private List<Subcat> data;
    @SerializedName("Type")
    @Expose
    private String type;

    public List<Subcat> getData() {
        return data;
    }

    public void setData(List<Subcat> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
